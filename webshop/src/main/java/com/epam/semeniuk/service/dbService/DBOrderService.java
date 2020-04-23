package com.epam.semeniuk.service.dbService;

import com.epam.semeniuk.dao.OrderDAO;
import com.epam.semeniuk.dao.transaction.TransactionManager;
import com.epam.semeniuk.entity.*;
import com.epam.semeniuk.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBOrderService implements OrderService {

    private OrderDAO orderDAO;
    private TransactionManager transactionManager;

    public DBOrderService(OrderDAO orderDAO, TransactionManager transactionManager) {
        this.orderDAO = orderDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean saveOrder(Cart cart, Order order) {
       return transactionManager.execute(connection -> orderDAO.saveOrder(connection, order, cart));
    }

}
