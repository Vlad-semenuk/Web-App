package com.epam.semeniuk.filter;

import com.epam.semeniuk.locale.ApplicationSupportedLocale;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.epam.semeniuk.constant.Constant.LOCALE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LocaleFilterTest {

    private static final String RU = "ru";
    private static final String EN = "en";
    public static final String LOCALE_EN = "localeEN";
    public static final String LOCALE_RU = "localeRU";
    public static final String DEFAULT_LOCALE = "defaultLocale";
    public static final String SESSION = "session";
    public static final String LOCALE_STORAGE = "localeStorage";
    public static final String COOKIE_LIFE_TIME = "cookieLifeTime";
    private static final String LOCALE = "locale";
    private static final String LANG = "lang";
    private static final String UA = "ua";
    private static final String FR = "fr";
    private static final String WRONG_PARAM = "wrong param";

    @InjectMocks
    private LocaleFilter localeFilter = new LocaleFilter();

    @Mock
    private ApplicationSupportedLocale supportedLocale;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpSession session;


    @Before
    public void setUp() throws ServletException {
        List<Locale> locales = new ArrayList<>();
        Locale ruLocale = new Locale(RU);
        Locale enLocale = new Locale(EN);
        locales.add(ruLocale);
        locales.add(enLocale);

        List<String> nameParameters = new ArrayList<>();
        nameParameters.add(LOCALE_EN);
        nameParameters.add(LOCALE_RU);
        nameParameters.add(LOCALE_STORAGE);
        when(request.getLocales()).thenReturn(Collections.enumeration(locales));

        when(filterConfig.getInitParameter(LOCALE_EN)).thenReturn(EN);
        when(filterConfig.getInitParameter(LOCALE_RU)).thenReturn(RU);
        when(filterConfig.getInitParameter(DEFAULT_LOCALE)).thenReturn(EN);
        when(filterConfig.getInitParameter(LOCALE_STORAGE)).thenReturn(SESSION);
        when(filterConfig.getInitParameterNames()).thenReturn(Collections.enumeration(nameParameters));
        when(filterConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(COOKIE_LIFE_TIME)).thenReturn("30");
        localeFilter.init(filterConfig);

    }

    @Test
    public void verifyCallSaveRuLocaleInSessionAttribute() throws IOException, ServletException {
        Locale ruLocale = new Locale(RU);
        when(request.getParameter(LANG)).thenReturn(RU);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(null);

        localeFilter.doFilter(request, response, filterChain);
        verify(session).setAttribute(LOCALE, ruLocale);
    }

    @Test
    public void verifyCallSaveEnLocaleInSessionAttribute() throws IOException, ServletException {
        Locale enLocale = new Locale(EN);
        when(request.getParameter(LANG)).thenReturn(EN);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(null);

        localeFilter.doFilter(request, response, filterChain);
        verify(session).setAttribute(LOCALE, enLocale);
    }


    @Test
    public void testSaveLocaleIfLocaleNotSelectedShouldSavePriorityLocale() throws IOException, ServletException {
        Locale ruLocale = new Locale(RU);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(null);

        localeFilter.doFilter(request, response, filterChain);
        verify(session).setAttribute(LOCALE, ruLocale);
    }

    @Test
    public void testSaveLocaleIfLocaleStorageNotEmptyShouldNonCalledSearchPriorityLocale() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(null);

        localeFilter.doFilter(request, response, filterChain);
        verify(supportedLocale, times(0)).searchPriorityLocale(request.getLocales());
    }


    @Test
    public void testSaveLocaleShouldBeSaveDefaultLocale() throws IOException, ServletException {
        Locale enLocale = new Locale(EN);
        List<Locale> locales = new ArrayList<>();
        Locale uaLocale = new Locale(UA);
        Locale frLocale = new Locale(FR);
        locales.add(uaLocale);
        locales.add(frLocale);
        when(request.getLocales()).thenReturn(Collections.enumeration(locales));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(null);

        localeFilter.doFilter(request, response, filterChain);
        verify(session).setAttribute(LOCALE, enLocale);
    }

    @Test
    public void testSaveLocaleWithWrongURLParamShouldBeSaveFirstPriorityLocale() throws IOException, ServletException {
        Locale ruLocale = new Locale(RU);
        when(request.getParameter(LANG)).thenReturn(WRONG_PARAM);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn(null);

        localeFilter.doFilter(request, response, filterChain);
        verify(session).setAttribute(LOCALE, ruLocale);
    }

}