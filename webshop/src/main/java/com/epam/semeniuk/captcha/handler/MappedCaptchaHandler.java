package com.epam.semeniuk.captcha.handler;

import com.epam.semeniuk.captcha.Captcha;


import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public abstract class MappedCaptchaHandler implements CaptchaHandler {

    protected ConcurrentMap<UUID, Captcha> captchaMap;

    public MappedCaptchaHandler(ConcurrentMap<UUID, Captcha> captchaMap) {
        this.captchaMap = captchaMap;
    }
}
