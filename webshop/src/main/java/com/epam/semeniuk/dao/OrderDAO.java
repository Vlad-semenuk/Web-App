package com.epam.semeniuk.dao;

import com.epam.semeniuk.entity.Cart;
import com.epam.semeniuk.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO {

    boolean saveOrder(Connection connection, Order order, Cart cart) throws SQLException;

}
