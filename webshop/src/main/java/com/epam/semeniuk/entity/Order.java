package com.epam.semeniuk.entity;

import com.epam.semeniuk.enums.Delivery;
import com.epam.semeniuk.enums.OrderStatus;
import com.epam.semeniuk.enums.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private OrderStatus orderStatus;
    private LocalDate localDate;
    private String userLogin;
    private List<OrderEntry> orderEntries;
    private String userAddress;
    private String userCard;
    private Delivery deliveryMethod;
    private Payment paymentMethod;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public boolean addOrderedItems(OrderEntry orderEntry) {
        if (orderEntries != null) {
            return orderEntries.add(orderEntry);
        }

        orderEntries = new ArrayList<>();
        return orderEntries.add(orderEntry);
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public Delivery getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(Delivery deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderStatus=" + orderStatus +
                ", localDate=" + localDate +
                ", userLogin='" + userLogin + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userCard=" + userCard +
                '}';
    }
}
