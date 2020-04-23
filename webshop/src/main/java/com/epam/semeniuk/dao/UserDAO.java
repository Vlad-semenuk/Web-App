package com.epam.semeniuk.dao;


import com.epam.semeniuk.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO {

    Optional<User> getUserByLogin(Connection connection, String login) throws SQLException;

    boolean saveUser(Connection connection, User user) throws SQLException;

    Optional<User> getUserByLoginAndPassword(Connection connection, String login, String password) throws SQLException;
}
