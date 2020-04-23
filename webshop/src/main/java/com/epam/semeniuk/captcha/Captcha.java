package com.epam.semeniuk.captcha;

import java.util.UUID;

public class Captcha {

    private UUID id;
    private String value;
    private long lifetime;
    private long timeOfCreation;

    public Captcha() {}

    public Captcha(UUID id, String value, long lifetime, long timeOfCreation) {
        this.id = id;
        this.value = value;
        this.lifetime = lifetime;
        this.timeOfCreation = timeOfCreation;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public long getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(long timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "value='" + value + '\'' +
                ", lifetime=" + lifetime +
                '}';
    }
}
