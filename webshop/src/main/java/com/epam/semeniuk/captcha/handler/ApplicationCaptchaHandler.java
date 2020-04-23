package com.epam.semeniuk.captcha.handler;


import com.epam.semeniuk.captcha.Captcha;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class ApplicationCaptchaHandler extends MappedCaptchaHandler {

    private static final Logger LOG = Logger.getLogger(ApplicationCaptchaHandler.class);
    private static final String CAPTCHA_ID = "CaptchaId";

    public ApplicationCaptchaHandler(ConcurrentMap<UUID, Captcha> captchaMap) {
        super(captchaMap);
    }

    @Override
    public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        captchaMap.put(captcha.getId(), captcha);
        LOG.debug("Set the application attribute -->" + captcha.getId());
        request.setAttribute(CAPTCHA_ID, captcha.getId());
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        String id = request.getParameter(CAPTCHA_ID);
        LOG.trace("Request parameter: " + CAPTCHA_ID + " --> " + id);
        UUID captchaId = null;
        if (Objects.nonNull(id) && !id.isEmpty()) {
            captchaId = UUID.fromString(id);
        }
        return captchaMap.get(captchaId);
    }
}
