package com.epam.semeniuk.common.validator.impl;

import com.epam.semeniuk.common.validator.Validator;
import com.epam.semeniuk.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.epam.semeniuk.constant.Constant.*;

public class LoginValidator implements Validator<HttpServletRequest, UserService> {

    @Override
    public Map<String, String> validate (HttpServletRequest request, UserService userService){
        Map<String, String> error = new HashMap<>();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (login == null || !isLoginValid(login) ){
            error.put(LOGIN_ERROR_MSG_PATH, LOGIN_ERROR_MSG);
        }
        if (password == null || !isPasswordValid(password)){
            error.put(PASSWORD_ERROR_MSG_PATH, PASSWORD_ERROR_MSG);
        }

        if (error.isEmpty()){
            if (!userService.isUserExists(login, password)){
                error.put(USER_NOT_FOUND_ERROR_PATH, USER_NOT_FOUND);
            }
        }
        return error;
    }

    private boolean isLoginValid(String login){
        return login.matches(LOGIN_REGEXP);
    }

    private boolean isPasswordValid(String password){
        return password.matches(PASSWORD_REGEXP);
    }

}
