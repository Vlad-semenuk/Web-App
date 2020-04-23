package com.epam.semeniuk.common.extractor.impl;

import com.epam.semeniuk.captcha.Captcha;
import com.epam.semeniuk.common.extractor.Extractor;


import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

public class CaptchaExtractor implements Extractor<Captcha> {

    private static final String CONFIRM_CAPTCHA_PARAMETER = "confirm-captcha";
    private static final String CAPTCHA_ID = "CaptchaId";

    @Override
    public Captcha extractFromRequest(HttpServletRequest request) {
        String captchaValue = request.getParameter(CONFIRM_CAPTCHA_PARAMETER);
        Captcha captcha = new Captcha();
        captcha.setValue(captchaValue);
        String captchaId = request.getParameter(CAPTCHA_ID);
        if (Objects.nonNull(captchaId)) {
            UUID id = UUID.fromString(captchaId);
            captcha.setId(id);
        }
        return captcha;
    }
}

