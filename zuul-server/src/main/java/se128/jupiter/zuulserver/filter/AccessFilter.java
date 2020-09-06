package se128.jupiter.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import se128.jupiter.zuulserver.service.SsoFeign;
import se128.jupiter.zuulserver.util.msgutils.Msg;
import se128.jupiter.zuulserver.util.msgutils.MsgCode;
import se128.jupiter.zuulserver.util.msgutils.MsgUtil;
import se128.jupiter.zuulserver.util.requestutils.RequestUtil;
import sun.swing.StringUIClientPropertyKey;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;

public class AccessFilter extends ZuulFilter {
    public AccessFilter(){}

    @Autowired
    private SsoFeign ssoFeign;
    @Autowired
    private RequestUtil requestUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        // 访问路径
        StringBuilder url = new StringBuilder(request.getRequestURL().toString());

        // 获取cookies
        String accessToken  = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("accessToken".equals(cookie.getName())){
                    accessToken = cookie.getValue();
                }
            }
        }

        // 过滤规则
        // 不需要登录也能访问
        if (
                url.toString().contains("goods/getPopularGoods") ||
                url.toString().contains("goods") ||
                url.toString().contains("goods/search") ||
                url.toString().contains("goods/getAllGoods") ||
                url.toString().contains("goods/getRecommendGoods") ||
                url.toString().contains("goods/getAllAuctions") ||
                url.toString().contains("goods/getAuctionByAuctionId") ||
                url.toString().contains("sso/login") ||
                url.toString().contains("sso/checkSession")
        ){
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);

            System.out.println("WITHOUT LOGIN");
        }
        else if(!StringUtils.isEmpty(accessToken) && ssoFeign.hasKey(accessToken)){
            JSONObject userInfo = ssoFeign.getKeyValue(accessToken);
            int userType = userInfo.getInt("userType");
            String urlString = url.toString();
            System.out.println(urlString);
            // 过滤goods-service需要权限的接口
            if(urlString.contains("goods/addGoods") || urlString.contains("goods/delete") ||
                    urlString.contains("goods/editGoods") || urlString.contains("goods/addAuction") ||
                    urlString.contains("goods/deleteAuctionByAuctionId") || urlString.contains("goods/editAuction")){
                if (userType == 0){ // 管理员
                    ctx.setSendZuulResponse(true);
                    ctx.setResponseStatusCode(200);
                }
                else {
                    requestUtil.sendJsonBack(response);
                }
                return null;
            }
            // 过滤order-service需要权限的接口
            else if(urlString.contains("/order") && request.getMethod().equals("GET")){
                if (userType == 0){ // 管理员
                    ctx.setSendZuulResponse(true);
                    ctx.setResponseStatusCode(200);
                }
                else {
                    requestUtil.sendJsonBack(response);
                }
                return null;
            }
            // 过滤user-service需要权限的接口
            else if(urlString.contains("user/getAllUsers") || urlString.contains("user/editUser") ||
                    urlString.contains("user/ban") || urlString.contains("user/unban") ||
                    urlString.contains("user/changeUserStatusByUserId") || urlString.contains("user/checkUser")){
                if (userType == 0){ // 管理员
                    ctx.setSendZuulResponse(true);
                    ctx.setResponseStatusCode(200);
                }
                else {
                    requestUtil.sendJsonBack(response);
                }
                return null;
            }
            else{
                ctx.setSendZuulResponse(true);
                ctx.setResponseStatusCode(200);
            }
        }
        else {  // 拒绝访问
            // send json back
            requestUtil.sendJsonBack(response);
        }
        return null;
    }
}
