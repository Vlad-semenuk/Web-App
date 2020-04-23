package com.epam.semeniuk.servlet;


import com.epam.semeniuk.common.validator.impl.LoginValidator;
import com.epam.semeniuk.entity.User;
import com.epam.semeniuk.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Map;


import static com.epam.semeniuk.constant.Constant.*;
import static com.epam.semeniuk.constant.Paths.LOGIN_PAGE;
import static com.epam.semeniuk.constant.Paths.MAIN_PAGE;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(LoginServlet.class);
    private static final String ERRORS = "errors";
    private static final String LOGIN_SERVLET = "/login";



    private UserService userService;
    private LoginValidator loginValidator;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        loginValidator = (LoginValidator) getServletContext().getAttribute(LOGIN_VALIDATOR);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Go to forward address --> {}", LOGIN_PAGE);
        getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> error = loginValidator.validate(req, userService);
        if (error.isEmpty()){
           User user =  userService.findUserByLogin(req.getParameter(LOGIN)).get();
           req.getSession().setAttribute(USER, user);
           resp.sendRedirect(MAIN_PAGE);
        } else {
            backToLogin(req, resp, error);
        }
    }

    private void backToLogin(HttpServletRequest request, HttpServletResponse response, Map<String, String> errors) throws IOException {
        request.getSession().setAttribute(ERRORS, errors);
        LOG.info("Set the session attribute: {}",  errors);
        response.sendRedirect(LOGIN_SERVLET);
    }
}
