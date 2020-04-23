package com.epam.semeniuk.service;

import com.epam.semeniuk.entity.Cart;
import com.epam.semeniuk.entity.Order;
import com.epam.semeniuk.entity.User;

public interface OrderService {

    boolean saveOrder(Cart cart, Order order);
}
