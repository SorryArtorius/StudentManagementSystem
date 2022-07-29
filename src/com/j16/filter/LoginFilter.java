package com.j16.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Master
 * 拦截器
 */
@WebFilter("/adminServlet")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("adminUserServlet?tag=login");
        } else {
            System.out.println("已登录");
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
