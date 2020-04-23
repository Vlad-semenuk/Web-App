package com.epam.semeniuk.dao.postgresql;

import com.epam.semeniuk.dao.OrderDAO;
import com.epam.semeniuk.entity.Cart;
import com.epam.semeniuk.entity.Order;
import com.epam.semeniuk.entity.OrderEntry;
import com.epam.semeniuk.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostgresqlOrderDAO implements OrderDAO {
    private static final Logger LOG = LogManager.getLogger(PostgresqlOrderDAO.class);

    private static final String CREATE_ORDER = "INSERT INTO orders (order_date, order_user_login, order_user_address, order_user_card, order_status, order_delivery_method, order_payment_method) VALUES(?,?,?,?,?,?,?);";
    private static final String CREATE_ORDER_ENTRY = "INSERT INTO order_entry(entry_product_id, entry_order_id, entry_product_quantity, entry_total_price) VALUES(?,?,?,?);";


    @Override
    public boolean saveOrder(Connection connection, Order order, Cart cart) throws SQLException {
        int orderId = createOrder(connection, order);
        if (orderId > 0){
            return createOrderedItems(connection, createListItems(cart.getAllItems(), orderId));
        }
        return false;
    }

    private int createOrder(Connection connection, Order order) throws SQLException {
        LOG.debug("Start order creating");
        int resultId = 0;
        int k = 1;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(k++, Date.valueOf(order.getLocalDate()));
            statement.setString(k++, order.getUserLogin());
            statement.setString(k++, order.getUserAddress());
            statement.setString(k++, order.getUserCard());
            statement.setInt(k++, order.getOrderStatus().getStatusId());
            statement.setInt(k++, order.getDeliveryMethod().getDeliveryId());
            statement.setInt(k++, order.getPaymentMethod().getPayment());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                resultId = resultSet.getInt(1);
            }
            resultSet.close();
            LOG.info("Saved order in DB : order --> {} ", order);

            return resultId;
        }
    }


    private boolean createOrderedItems(Connection connection, List<OrderEntry> orderedItems) throws SQLException {
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
        LOG.info("Count ordered items --> {}", orderedItems.size());
        return true;
    }

    private List<OrderEntry> createListItems(Map<Product, Integer> items, int orderId) {
        List<OrderEntry> orderedItems = new ArrayList<>();
        items.forEach((key, value) -> orderedItems.add(createOrderedItem(key, value, orderId)));
        return orderedItems;
    }

    private OrderEntry createOrderedItem(Product item, int countOfItem, int orderId) {
        return new OrderEntry(orderId, item.getId(), countOfItem, item.getPrice());
    }
}
