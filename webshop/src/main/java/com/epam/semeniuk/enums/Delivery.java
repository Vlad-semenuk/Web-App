package com.epam.semeniuk.enums;

import java.util.Arrays;

public enum Delivery {

    BY_MAIL("by_mail", 1), COURIER("courier",2), SELF_PICKUP("self-pickup", 3);

    private final String delivery;
    private final int id;

    Delivery(String delivery, int id){
         this.delivery = delivery;
         this.id = id;
    }

    public static Delivery getDeliveryMethodById(int id) {
        return Arrays.stream(Delivery.values()).filter(i -> i.id == id).findFirst().orElse(null);
    }

    public int getDeliveryId() {
        return this.id;
    }

    public String getName() {
        return this.delivery;
    }
}
