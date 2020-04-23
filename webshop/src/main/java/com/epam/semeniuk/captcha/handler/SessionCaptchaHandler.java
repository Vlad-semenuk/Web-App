package com.epam.semeniuk.captcha.handler;


import com.epam.semeniuk.captcha.Captcha;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionCaptchaHandler implements CaptchaHandler{

    private static final Logger LOG = Logger.getLogger(SessionCaptchaHandler.class);

    private static final String CAPTCHA = "captcha";

    @Override
    public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        LOG.debug("Set the session attribute --> " + captcha.getId());
        request.getSession().setAttribute(CAPTCHA, captcha);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        Captcha captcha = (Captcha) request.getSession().getAttribute(CAPTCHA);
        request.getSession().removeAttribute(CAPTCHA);
        return captcha;
    }
}
