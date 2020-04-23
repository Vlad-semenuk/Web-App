package com.epam.semeniuk.captcha.handler;

import com.epam.semeniuk.captcha.Captcha;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaHandler {

    void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha);

    Captcha getCaptcha(HttpServletRequest request);
}
