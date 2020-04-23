package com.epam.semeniuk.locale.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.epam.semeniuk.constant.Constant.LOCALE;

public class SessionLocaleStorage implements LocaleStorage {

    @Override
    public Locale getLocale(HttpServletRequest request) {
        return (Locale) request.getSession().getAttribute(LOCALE);
    }

    @Override
    public void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        request.getSession().setAttribute(LOCALE, locale);
    }
}
