package com.epam.semeniuk.locale.storage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Objects;
import static com.epam.semeniuk.constant.Constant.LOCALE;

public class CookieLocaleStorage implements LocaleStorage{

    private int cookieLife;

    public CookieLocaleStorage (int cookieLife){
        this.cookieLife = cookieLife;
    }
    @Override
    public Locale getLocale(HttpServletRequest request) {
        return getLocaleFromCookies(request.getCookies());
    }

    @Override
    public void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        Cookie cookie = new Cookie(LOCALE, locale.getLanguage());
        cookie.setMaxAge(cookieLife);
        response.addCookie(cookie);
    }

    private Locale getLocaleFromCookies(Cookie[] cookies) {
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LOCALE)) {
                    return new Locale(cookie.getValue());
                }
            }
        }
        return null;
    }
}
