package com.epam.semeniuk.common.validator.impl;

import com.epam.semeniuk.common.validator.Validator;
import com.epam.semeniuk.dto.UserDTO;
import com.epam.semeniuk.service.UserService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.*;


public class UserValidator implements Validator<UserDTO, UserService> {

    private static final Logger LOG = Logger.getLogger(UserValidator.class);


    @Override
    public Map<String, String> validate(UserDTO userDTO, UserService userService) {
        LOG.debug("User validator starts.");
        Map<String, String> errors = new HashMap<>();

        if (userDTO.getLogin() == null || !isLoginValid(userDTO.getLogin())) {
            errors.put(LOGIN_ERROR_MSG_PATH, LOGIN_ERROR_MSG);
        }
        if (userDTO.getFirstName() == null || !isFirstNameValid(userDTO.getFirstName())) {
            errors.put(FIRST_NAME_ERROR_MSG_PATH, FIRST_NAME_ERROR_MSG);
        }
        if (userDTO.getLastName() == null || !isLastNameValid(userDTO.getLastName())) {
            errors.put(LAST_NAME_ERROR_MSG_PATH, LAST_NAME_ERROR_MSG);
        }
        if (userDTO.getEmail() == null || !isEmailValid(userDTO.getEmail())) {
            errors.put(EMAIL_ERROR_MSG_PATH, EMAIL_ERROR_MSG);
        }
        if (userDTO.getPassword() == null || !isPasswordValid(userDTO.getPassword())) {
            errors.put(PASSWORD_ERROR_MSG_PATH, PASSWORD_ERROR_MSG);
        }
        if (userDTO.getConfirmPassword() == null || !isConfirmPasswordValid(userDTO.getConfirmPassword(), userDTO.getPassword())) {
            errors.put(CONFIRM_PASSWORD_ERROR_MSG_PATH, CONFIRM_PASSWORD_ERROR_MSG);
        }

        if (errors.isEmpty()){
            if (userService.findUserByLogin(userDTO.getLogin()).isPresent()) {
                errors.put(USER_CREATE_ERROR_PATH, USER_ALREADY_EXIST);
            }
        }
        return errors;
    }

    private boolean isLoginValid(String login) {
        return login.matches(LOGIN_REGEXP);
    }

    private boolean isFirstNameValid(String firstName) {
        return firstName.matches(NAME_REGEXP);
    }

    private boolean isLastNameValid(String lastName) {
        return lastName.matches(NAME_REGEXP);
    }

    private boolean isEmailValid(String email) {
        return email.matches(EMAIL_REGEXP);
    }

    private boolean isPasswordValid(String password) {
        return password.matches(PASSWORD_REGEXP);
    }

    private boolean isConfirmPasswordValid(String confirmPassword, String password) {
        return confirmPassword.equals(password);
    }


}
