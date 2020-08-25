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
        if (url.toString().contains("sso/login") ||
                url.toString().contains("sso/checkSession") ||
                url.toString().contains("goods/getAllGoods") ||
                url.toString().contains("goods/getPopularGoods") ||
                (!StringUtils.isEmpty(accessToken) && ssoFeign.hasKey(accessToken))){
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);

            System.out.println("INNNNNNNNNNNNN");
        }
        else{   // 拒绝访问
            // send json back
            Msg msg = MsgUtil.makeMsg(MsgCode.NOT_AVAILABLE);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try (PrintWriter writer = response.getWriter()){
                writer.print(JSONObject.fromObject(msg));
            }catch (IOException e){
                System.out.println("send json back error");
            }

            System.out.println("OUTTTTTTTTTTTTTT");
        }
        return null;
    }
}
