package com.epam.semeniuk.filter;

import com.epam.semeniuk.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.semeniuk.constant.Paths.MAIN_PAGE;

@WebFilter(urlPatterns = {"/login", "/register"})
public class AuthenticationFilter implements Filter {

    private static final String USER = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //nothing to do
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (isUserLogin(request)) {
            response.sendRedirect(MAIN_PAGE);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean isUserLogin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER);
        return user != null;
    }
}
