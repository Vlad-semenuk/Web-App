package com.epam.semeniuk.enums;

import java.util.Arrays;

public enum  OrderStatus {
    ACCEPTED("accepted", 1), CONFIRMED("confirmed", 2), FORMED("formed", 3), SENT("sent", 4), COMPLETED("completed", 5), CANCELED("canceled", 6);

    private final String status;
    private final int id;

    OrderStatus(String status, int id) {
        this.status = status;
        this.id = id;
    }

    public static OrderStatus getStatus(int id) {
        return Arrays.stream(OrderStatus.values()).filter(i -> i.id == id).findFirst().orElse(null);
    }

    public int getStatusId() {
        return this.id;
    }

    public String getName() {
        return this.status;
    }
}
