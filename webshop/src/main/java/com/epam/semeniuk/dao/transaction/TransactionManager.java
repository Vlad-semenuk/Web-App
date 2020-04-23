package com.epam.semeniuk.dao.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger LOG = LogManager.getLogger(TransactionManager.class);

    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T execute(TransactionOperation<T> operation) {
        T result = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            result = operation.produce(connection);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            LOG.error("Cannot commit operation. ", e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException exc) {
                LOG.error("Cannot rollback operation. ", exc);
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("Cannot close a connection.", e);
                }
            }
        }
        return result;
    }
}
