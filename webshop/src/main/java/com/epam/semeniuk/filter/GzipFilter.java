package com.epam.semeniuk.filter;

import com.epam.semeniuk.gzip.GZipServletResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
public class GzipFilter implements Filter {

    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String GZIP = "gzip";
    private static final String CONTENT_TYPE = "text/html";
    private static final String AVATAR_URL = "/avatar";
    private static final String IMAGES_URL = "/images";


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (acceptsGZipEncoding(httpRequest)) {
            httpResponse.setContentType(CONTENT_TYPE);
            httpResponse.addHeader(CONTENT_ENCODING, GZIP);
            GZipServletResponse gzipResponse = new GZipServletResponse(httpResponse);
            chain.doFilter(request, gzipResponse);
            gzipResponse.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest request) {
        String contestType = request.getContentType();
        if (Objects.nonNull(contestType)){
            return contestType.contains(CONTENT_TYPE);
        }
        return !acceptsURL(request.getRequestURI()) ;
    }

    private boolean acceptsURL(String uri){
        return uri.startsWith(AVATAR_URL) && uri.startsWith(IMAGES_URL);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //nothing to do
    }

    @Override
    public void destroy() {
        //nothing to do
    }
}

