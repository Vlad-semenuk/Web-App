package com.epam.semeniuk.enums;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.util.Arrays;

public enum Payment {
    VISA("visa", 1), MASTERCARD("mastercard", 2);

    private final String payment;
    private final int id;

    Payment(String payment, int id) {
        this.payment = payment;
        this.id = id;
    }

    public static Payment getPaymentMethodById(int id) {
        return Arrays.stream(Payment.values()).filter(i -> i.id == id).findFirst().orElse(null);
    }

    public int getPayment() {
        return this.id;
    }

    public String getName() {
        return this.payment;
    }
}
