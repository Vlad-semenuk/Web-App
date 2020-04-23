package com.epam.semeniuk.service.dbService;

import com.epam.semeniuk.dao.UserDAO;
import com.epam.semeniuk.dao.transaction.TransactionManager;
import com.epam.semeniuk.dto.UserDTO;
import com.epam.semeniuk.entity.User;
import com.epam.semeniuk.service.UserService;

import java.util.Optional;

public class DBUserService implements UserService {

    private TransactionManager transactionManager;
    private UserDAO userDAO;

    public DBUserService(TransactionManager transactionManager, UserDAO userDAO) {
        this.transactionManager = transactionManager;
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return transactionManager.execute(connection -> userDAO.getUserByLogin(connection, login));
    }


    @Override
    public boolean saveUser(UserDTO userDTO) {
        return transactionManager.execute(connection -> userDAO.saveUser(connection, userDTO.toUser()));
    }

    @Override
    public boolean isUserExists(String login, String password) {
        return transactionManager.execute(connection -> userDAO.getUserByLoginAndPassword(connection, login, password)).isPresent();
    }
}
