package com.epam.semeniuk.filter;


import com.epam.semeniuk.common.extractor.ExtractorUtils;
import com.epam.semeniuk.exception.AppException;
import com.epam.semeniuk.locale.ApplicationSupportedLocale;
import com.epam.semeniuk.locale.storage.CookieLocaleStorage;
import com.epam.semeniuk.locale.storage.LocaleStorage;
import com.epam.semeniuk.locale.storage.SessionLocaleStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.epam.semeniuk.constant.Constant.*;


public class LocaleFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger(LocaleFilter.class);
    private static final String LOCALE_SUFFIX = "locale";

    private LocaleStorage localeStorage;
    private ApplicationSupportedLocale supportedLocale;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {

            localeStorage = initLocaleStorage(filterConfig);
            supportedLocale = initSupportedLocale(filterConfig);
        } catch (AppException e) {
            LOG.error(e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Locale locale = localeStorage.getLocale(request);
        String languageName = request.getParameter(LANG);

        if (Objects.nonNull(languageName)) {
            locale = supportedLocale.getLocale(languageName);
        }

        if (Objects.isNull(locale)) {
            locale = supportedLocale.searchPriorityLocale(request.getLocales());
        }

        localeStorage.saveLocale(request, response, locale);
        filterChain.doFilter(replaceLocale(request, locale), servletResponse);
    }

    private LocaleStorage initLocaleStorage(FilterConfig filterConfig) throws AppException {
        String storageName = filterConfig.getInitParameter(LOCALE_STORAGE);
        Integer cookieLife = ExtractorUtils.checkInteger(filterConfig.getServletContext().getInitParameter(COOKIE_LIFE_TIME));
        if (Objects.nonNull(cookieLife) && Objects.nonNull(storageName)) {
            switch (storageName) {
                case SESSION:
                    return new SessionLocaleStorage();
                case COOKIE:
                    return new CookieLocaleStorage(cookieLife);
                default:
                    throw new AppException("Can not init locale storage");
            }
        }
        throw new AppException("Can not init locale storage");
    }

    private ApplicationSupportedLocale initSupportedLocale(FilterConfig filterConfig) throws AppException {
        String defaultLocale = filterConfig.getInitParameter(DEFAULT_LOCALE);
        Enumeration<String> parameterNames = filterConfig.getInitParameterNames();
        List<String> supportedLocale = new ArrayList<>();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith(LOCALE_SUFFIX)) {
                supportedLocale.add(filterConfig.getInitParameter(paramName));
            }
        }
        if (!supportedLocale.isEmpty() && supportedLocale.contains(defaultLocale)){
            return new ApplicationSupportedLocale(supportedLocale, defaultLocale);
        }
        throw new AppException("Can not init supported locale");
    }

    private HttpServletRequest replaceLocale(HttpServletRequest request, Locale locale) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public Locale getLocale() {
                return locale;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return Collections.enumeration(supportedLocale.replaceGetLocales());
            }
        };
    }


    @Override
    public void destroy() {
        //nothing to do
    }
}
