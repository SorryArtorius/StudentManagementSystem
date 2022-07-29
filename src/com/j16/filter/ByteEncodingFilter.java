package com.j16.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @author Master
 */
@WebFilter(value = "/*",
        /**
         * 加载项设置参数
        */
        initParams = {
        @WebInitParam(name = "character", value = "utf-8")
})
public class ByteEncodingFilter implements Filter {

    public String character;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /**
         * 读取启动参数
         */
        character = filterConfig.getInitParameter("character");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        /**
         * 设置全局字符编码
         */
        servletRequest.setCharacterEncoding(character);
        servletResponse.setCharacterEncoding(character);
        /**
         * 释放
         */
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
