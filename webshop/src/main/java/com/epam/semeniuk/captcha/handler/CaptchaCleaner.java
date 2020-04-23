package com.epam.semeniuk.captcha.handler;


import com.epam.semeniuk.captcha.Captcha;
import org.apache.log4j.Logger;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class CaptchaCleaner extends Thread {

    private static final Logger LOG = Logger.getLogger(CaptchaCleaner.class);
    private long lifetime;

    private ConcurrentMap<UUID, Captcha> captchaMap;

    public CaptchaCleaner(ConcurrentMap<UUID, Captcha> captchaMap, long lifetime) {
        this.captchaMap = captchaMap;
        this.lifetime = lifetime;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(lifetime);
            } catch (InterruptedException e) {
                LOG.error(" CaptchaMapCleaner interrupted.");
            }
            LOG.info("Cleaning captchaMap. ");
            removeDeadCaptcha();
        }
    }

    private void removeDeadCaptcha() {
        captchaMap.values().removeIf(captcha -> (System.currentTimeMillis() - captcha.getTimeOfCreation()) > captcha.getLifetime());
    }
}
