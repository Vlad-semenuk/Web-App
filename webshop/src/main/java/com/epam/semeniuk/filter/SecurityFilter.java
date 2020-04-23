package com.epam.semeniuk.filter;

import com.epam.semeniuk.common.security.JaxbParserXmlSecurity;
import com.epam.semeniuk.common.security.Security;
import com.epam.semeniuk.common.security.SecurityConfig;
import com.epam.semeniuk.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.ERRORS;
import static com.epam.semeniuk.constant.Constant.USER;
import static com.epam.semeniuk.constant.Paths.*;


@WebFilter("/*")
public class SecurityFilter implements Filter {

    private static final String LOGIN_SERVLET = "/login";
    private SecurityConfig securityConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        securityConfig = new JaxbParserXmlSecurity().getSecurityConfig();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute(USER);
        String url = request.getRequestURI();
        Security currentSecurity = getCurrentUrlSecurity(url);
        if(Objects.nonNull(currentSecurity)){
            if (Objects.nonNull(user)){
                if (currentSecurity.getRole().equals(user.getRole().getName())){
                    filterChain.doFilter(request, response);
                } else {
                    request.getSession().setAttribute(ERRORS, "Access denied");
                    response.sendRedirect(ERROR_PAGE);
                }
            }
            response.sendRedirect(LOGIN_SERVLET);
        } else {
            filterChain.doFilter(request, response);
        }
    }


    private Security getCurrentUrlSecurity(String url){
        for (Security security : securityConfig.getConstraint()){
            if (security.getUrl().contains(url)){
                return security;
            }
        }
        return null;
    }


    @Override
    public void destroy() {

    }
}
