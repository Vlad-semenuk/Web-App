package com.epam.semeniuk.common.validator.impl;

import com.epam.semeniuk.captcha.Captcha;
import com.epam.semeniuk.common.validator.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.*;

public class CaptchaValidator implements Validator<Captcha, Captcha> {

    @Override
    public Map<String, String> validate(Captcha requestCaptcha, Captcha storedCaptcha) {
        Map<String, String> error = new HashMap<>();
        if (isNullCaptchaValidate(requestCaptcha, storedCaptcha)) {
            if (!isEqualsCaptchaValidate(requestCaptcha, storedCaptcha)) {
                error.put(CAPTCHA_ERROR_PATH, CAPTCHA_WRONG_ERROR_MSG);
            }
            if (!isTimeoutCaptchaValidate(storedCaptcha)) {
                error.put(CAPTCHA_ERROR_PATH, CAPTCHA_TIMEOUT_ERROR_MSG);
            }
        } else {
            error.put(CAPTCHA_ERROR_PATH, CAPTCHA_EMPTY_ERROR_MSG);
        }
        return error;
    }

    private boolean isNullCaptchaValidate(Captcha captcha, Captcha storedCaptcha) {
        return Objects.nonNull(captcha) && Objects.nonNull(storedCaptcha);
    }

    private boolean isEqualsCaptchaValidate(Captcha captcha, Captcha storedCaptcha) {
        return captcha.getValue().equals(storedCaptcha.getValue());
    }

    private boolean isTimeoutCaptchaValidate(Captcha captcha) {
        return (System.currentTimeMillis() - captcha.getTimeOfCreation()) < captcha.getLifetime();
    }
}
