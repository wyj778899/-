package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class MyZuulFilter extends ZuulFilter {
    @Override
    /**
     * 配置方法: 通过方法返回值指定Filter类型
     pre ：可以在请求被路由之前调用
     route ：在路由请求时候被调用
     post ：在route和error过滤器之后被调用
     error ：处理请求时发生错误时被调用
     */

    public String filterType() {

        return "pre";
    }

    @Override
    /**
     * 配置方法:配置网关过滤器执行的优先级
     *      0 : 优先级最高
     *      数字越大优先级越低
     *
     */
    public int filterOrder() {
        return 0;
    }

    @Override
    /**
     * 配置方法: 配置是否启用该过滤器
     *      true: 启用
     *      false: 不启用
     */
    public boolean shouldFilter() {
        return true;
    }

    @Override
    /**
     * 网关过滤器逻辑方法 => 业务逻辑代码放入该方法中
     */
    public Object run() throws ZuulException {

        System.out.println("网关过滤器执行啦~~~~~");

        return null;
    }
}
