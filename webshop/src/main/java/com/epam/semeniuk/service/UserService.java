package com.epam.semeniuk.service;

import com.epam.semeniuk.dto.UserDTO;
import com.epam.semeniuk.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByLogin(String login);

    boolean saveUser(UserDTO userDTO);

    boolean isUserExists(String login, String password);

}
