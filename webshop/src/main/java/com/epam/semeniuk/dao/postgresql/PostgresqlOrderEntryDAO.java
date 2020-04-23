package com.epam.semeniuk.dao.postgresql;

import com.epam.semeniuk.dao.OrderEntryDAO;
import com.epam.semeniuk.entity.OrderEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class PostgresqlOrderEntryDAO implements OrderEntryDAO {
    private static final Logger LOG = LogManager.getLogger(PostgresqlOrderEntryDAO.class);
    private static final String CREATE_ORDER_ENTRY = "INSERT INTO order_entry(entry_product_id, entry_order_id, entry_product_quantity, entry_total_price) VALUES(?,?,?,?);";

    @Override
    public boolean createOrderedItems(Connection connection, List<OrderEntry> orderedItems) throws SQLException {
        LOG.debug("Start order entry creating");
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER_ENTRY)) {
            for (OrderEntry orderedItem : orderedItems) {
                statement.setInt(1, orderedItem.getProductId());
                statement.setInt(2, orderedItem.getOrderId());
                statement.setInt(3, orderedItem.getCountOfItems());
                statement.setBigDecimal(4, orderedItem.getPrice());
                statement.executeUpdate();
            }
        }
        LOG.info("Count ordered items --> {}",orderedItems.size());
        return true;
    }
}
