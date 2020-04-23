package com.epam.semeniuk.dao.postgresql;

import com.epam.semeniuk.dao.UserDAO;
import com.epam.semeniuk.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PostgresqlUserDAO implements UserDAO {

    private static final Logger LOG = Logger.getLogger(PostgresqlUserDAO.class);

    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
    private static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?;";
    private static final String SAVE_NEW_USER = "INSERT INTO users (login, first_name, last_name, email, password, spam, avatar, role_id) VALUES (?,?,?,?,?,?,?,?);";

    @Override
    public Optional<User> getUserByLogin(Connection connection, String login) throws SQLException {

        Optional<User> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = extractUser(resultSet);
            }
        }
        return result;
    }

    @Override
    public boolean saveUser(Connection connection, User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEW_USER)) {
            int k = 1;
            preparedStatement.setString(k++, user.getLogin());
            preparedStatement.setString(k++, user.getFirstName());
            preparedStatement.setString(k++, user.getLastName());
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setBoolean(k++, user.isSpam());
            preparedStatement.setString(k++, user.getAvatarExtension());
            preparedStatement.setInt(k++, user.getRole().getRoleId());

            preparedStatement.executeUpdate();
            LOG.info("Saved user in DB : user --> " + user.getLogin());
        }
        return true;
    }

    @Override
    public Optional<User> getUserByLoginAndPassword(Connection connection, String login, String password) throws SQLException {
        Optional<User> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = extractUser(resultSet);
            }
        }
        return result;
    }

    private Optional<User> extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setSpam(resultSet.getBoolean("spam"));
        user.setAvatarExtension(resultSet.getString("avatar"));
        user.setAvatarExtension(resultSet.getString("role_id"));
        LOG.info("Extracted user : user -->" + user.getLogin());
        return Optional.of(user);
    }
}
