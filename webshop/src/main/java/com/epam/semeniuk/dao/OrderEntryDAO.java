package com.epam.semeniuk.dao;

import com.epam.semeniuk.entity.OrderEntry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderEntryDAO {

    boolean createOrderedItems(Connection connection, List<OrderEntry> orderedItems) throws SQLException;
}
