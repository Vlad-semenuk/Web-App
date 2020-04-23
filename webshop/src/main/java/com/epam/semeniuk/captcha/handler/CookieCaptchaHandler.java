package com.epam.semeniuk.captcha.handler;


import com.epam.semeniuk.captcha.Captcha;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class CookieCaptchaHandler extends MappedCaptchaHandler {

    private static final Logger LOG = Logger.getLogger(CookieCaptchaHandler.class);
    private static final String CAPTCHA_ID = "CaptchaId";

    public CookieCaptchaHandler(ConcurrentMap<UUID, Captcha> captchaMap) {
        super(captchaMap);
    }

    @Override
    public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        captchaMap.put(captcha.getId(), captcha);
        Cookie cookie = new Cookie(CAPTCHA_ID, captcha.getId().toString());
        LOG.info("Add captcha to cookie: cookie --> " + cookie);
        response.addCookie(cookie);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        UUID captchaId = getCaptchaId(request.getCookies());
        LOG.info("Cookies captcha UUID: captchaId --> " + captchaId);
        return captchaMap.get(captchaId);
    }

    private UUID getCaptchaId(Cookie[] cookies) {
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CAPTCHA_ID)) {
                    return UUID.fromString(cookie.getValue());
                }
            }
        }
        return null;
    }
}
