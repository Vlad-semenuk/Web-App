package com.epam.semeniuk.dao.connection;



import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;

public class ConnectionPool {

    public static final int MIN_CONNECTION = 10;
    public static final int MAX_CONNECTION = 20;

    public static DataSource createDataSource(String url, String user, String password) throws SQLException {
        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setUrl(url);
        ds.setUser(user);
        ds.setPassword(password);
        ds.setInitialConnections(MIN_CONNECTION);
        ds.setMaxConnections(MAX_CONNECTION);
        return ds;
    }
}
