package com.epam.semeniuk.common.validator;

import com.epam.semeniuk.captcha.Captcha;
import com.epam.semeniuk.common.validator.impl.CaptchaValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class CaptchaValidatorTest {

    private static final String CAPTCHA_VALUE = "53225";
    private static final String WRONG_CAPTCHA_VALUE = "53205";

    private static final long LIFE_TIME = 60 * 1000;
    private CaptchaValidator captchaValidator;
    private Captcha captcha;

    @Before
    public void setUp() {
        captchaValidator = new CaptchaValidator();
        captcha = new Captcha(null, CAPTCHA_VALUE, LIFE_TIME, System.currentTimeMillis());
    }

    @Test
    public void validateCaptchaShouldBeEmptyErrorsMap() {
        Captcha captchaRequest = new Captcha(null, CAPTCHA_VALUE, LIFE_TIME, System.currentTimeMillis());
        Map<String, String> error = captchaValidator.validate(captcha, captchaRequest);
        Assert.assertTrue(error.isEmpty());
    }

    @Test
    public void errorValidateCaptchaShouldBeNotEmptyErrorsMap() {
        Captcha captchaRequest = new Captcha(null, WRONG_CAPTCHA_VALUE, LIFE_TIME, System.currentTimeMillis());
        Map<String, String> error = captchaValidator.validate(captcha, captchaRequest);
        Assert.assertFalse(error.isEmpty());
    }
}