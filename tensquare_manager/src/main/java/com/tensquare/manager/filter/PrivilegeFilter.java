package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class PrivilegeFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Object run() throws ZuulException {

        //获得请求对象
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        //直接放行options请求方式的请求
        String method = request.getMethod();
        if(method.equals("options")){
            return null; //放行
        }
        //获得请求路径 => 判断是否为管理员登录路径 , 如果是->放行
        String path = request.getServletPath();
        if(path.endsWith("/login")){//登录请求
            return null;//放行
        }
        //获取Authorization头 => 解析token=>获得登录用户角色 => 判断是否为管理员

        //获取Authorization头
        String authorization = request.getHeader("Authorization");
        if(authorization!=null && authorization.startsWith("Bearer ")){ //判断头是否存在&& 以"Bearer "
            //提取token部分
            String token = authorization.substring(7);
            //解析token得到载荷
            Claims claims = jwtUtils.parseJwt(token);
            //判断解析成功&& 角色为管理员
            if(claims!=null  &&  claims.get("roles").equals("admin")){
                return null;//放行
            }
        }
        //拒绝转发,并提示
        rc.setSendZuulResponse(false);//拒绝转发
        rc.setResponseBody("<h1><font color='red'>您没有权限</font></h1>");//网关直接返回的响应正文
        rc.setResponseStatusCode(500);//网关返回状态码

        //解决中文乱码
        rc.getResponse().setContentType("text/html;charset=utf-8");

        return null;
    }
}
