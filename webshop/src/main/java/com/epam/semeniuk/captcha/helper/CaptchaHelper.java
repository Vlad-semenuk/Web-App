package com.epam.semeniuk.captcha.helper;

import com.epam.semeniuk.captcha.Captcha;

import com.github.cage.Cage;

import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class CaptchaHelper {

    private final int CAPTCHA_LENGTH = 5;
    private final long lifetime;
    private final Cage cage;

    public CaptchaHelper(Cage cage, long lifetime) {
        this.lifetime = lifetime;
        this.cage = cage;
    }

    public Captcha generateCaptcha() {
        String generatedValue = generateRandomNumber(CAPTCHA_LENGTH);
        return new Captcha(UUID.randomUUID(), generatedValue, lifetime, new Date().getTime());
    }

    public String drawCaptcha(Captcha captcha) {
        byte[] imageBytes = cage.draw(captcha.getValue());
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private String generateRandomNumber(int charLength) {
        return String.valueOf(charLength < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
                + (int) Math.pow(10, charLength - 1));
    }
}
