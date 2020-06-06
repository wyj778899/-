package com.tensquare.friend.Interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        //校验token
        //1.获取Authorization头
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)){
           return true;//放行
        }
        //2 截取出token部分
        if(!authorization.startsWith("Bearer ")){
            return true;//放行
        }
        String token = authorization.substring(7);
        //3 解析token
        Claims claims = jwtUtils.parseJwt(token);
        if(claims==null){
           return true;//放行
        }

        //将载荷存入request域
        if("admin".equals(claims.get("roles"))){
            request.setAttribute("claims_admin", claims);
        }else{
            request.setAttribute("claims_user", claims);
        }


        return true;
    }
}
