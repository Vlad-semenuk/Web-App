package com.epam.semeniuk.entity;

import java.math.BigDecimal;

public final class OrderEntry {
    private final int orderId;
    private final int productId;
    private final int countOfItems;
    private final BigDecimal price;

    public OrderEntry(int orderId, int productId, int countOfItems, BigDecimal price) {
        this.orderId = orderId;
        this.productId = productId;
        this.countOfItems = countOfItems;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getCountOfItems() {
        return countOfItems;
    }

    public BigDecimal getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "OrderEntry{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", countOfItems=" + countOfItems +
                ", price=" + price +
                '}';
    }
}
