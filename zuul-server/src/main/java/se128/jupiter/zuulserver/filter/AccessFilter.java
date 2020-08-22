package se128.jupiter.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import se128.jupiter.zuulserver.service.SsoFeign;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        ssoFeign.checkUsernameAndPassword("huanzi", "123456");

        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        return null;
    }
}
