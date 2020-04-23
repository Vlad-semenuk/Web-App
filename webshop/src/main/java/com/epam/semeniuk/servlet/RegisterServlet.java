package com.epam.semeniuk.servlet;

import com.epam.semeniuk.avatar.Avatar;
import com.epam.semeniuk.avatar.AvatarHelper;
import com.epam.semeniuk.captcha.Captcha;
import com.epam.semeniuk.captcha.handler.CaptchaHandler;
import com.epam.semeniuk.common.extractor.impl.AvatarExtractor;
import com.epam.semeniuk.common.extractor.impl.CaptchaExtractor;
import com.epam.semeniuk.common.extractor.impl.UserDTOExtractor;
import com.epam.semeniuk.common.validator.impl.AvatarValidator;
import com.epam.semeniuk.common.validator.impl.CaptchaValidator;
import com.epam.semeniuk.common.validator.impl.UserValidator;
import com.epam.semeniuk.constant.Constant;
import com.epam.semeniuk.dto.UserDTO;
import com.epam.semeniuk.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(RegisterServlet.class);
    private final static String ERRORS = "errors";
    private static final String LOGIN_SAVE = "login";
    private static final String FIRST_NAME_SAVE = "firstName";
    private static final String LAST_NAME_SAVE = "lastName";
    private static final String EMAIL_SAVE = "email";
    private static final String CAPTCHA_SERVLET = "/captcha";

    private UserService userService;
    private UserValidator userValidator;
    private CaptchaHandler captchaHandler;
    private CaptchaValidator captchaValidator;
    private AvatarHelper avatarHelper;
    private AvatarValidator avatarValidator;

    @Override
    public void init(){
        avatarValidator = (AvatarValidator) getServletContext().getAttribute(AVATAR_VALIDATOR);
        avatarHelper = (AvatarHelper) getServletContext().getAttribute(AVATAR_HELPER);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        userValidator = (UserValidator) getServletContext().getAttribute(USER_VALIDATE);
        captchaHandler = (CaptchaHandler) getServletContext().getAttribute(CAPTCHA_HANDLER);
        captchaValidator = (CaptchaValidator) getServletContext().getAttribute(CAPTCHA_VALIDATOR);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(CAPTCHA_SERVLET).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Avatar avatar = new AvatarExtractor().extractFromRequest(req);
        Captcha captcha = new CaptchaExtractor().extractFromRequest(req);
        Captcha storeCaptcha = captchaHandler.getCaptcha(req);
        Map<String, String> errors = new HashMap<>();
        UserDTO userDTO = new UserDTOExtractor().extractFromRequest(req);
        errors.putAll(userValidator.validate(userDTO, userService));
        errors.putAll(captchaValidator.validate(captcha, storeCaptcha));
        errors.putAll(avatarValidator.validate(avatar));
        if (errors.isEmpty()) {
            LOG.info("User login --> {}",  userDTO.getLogin());
            saveAvatar(avatar, userDTO);
            userService.saveUser(userDTO);
            resp.sendRedirect(req.getContextPath());
        } else {
            backToRegistration(req, resp, errors);
        }
    }

    private void backToRegistration(HttpServletRequest request, HttpServletResponse response, Map<String, String> errors) throws IOException {
        saveFieldValuesState(request);
        request.getSession().setAttribute(ERRORS, errors);
        LOG.info("Set the session attribute: --> {}",  errors);
        LOG.debug("Go to redirect address --> {}", CAPTCHA_SERVLET);
        response.sendRedirect(CAPTCHA_SERVLET);
    }

    private void saveFieldValuesState(HttpServletRequest request) {
        request.getSession().setAttribute(LOGIN_SAVE, request.getParameter(Constant.LOGIN));
        request.getSession().setAttribute(FIRST_NAME_SAVE, request.getParameter(Constant.FIRST_NAME));
        request.getSession().setAttribute(LAST_NAME_SAVE, request.getParameter(Constant.LAST_NAME));
        request.getSession().setAttribute(EMAIL_SAVE, request.getParameter(Constant.EMAIL));
    }

    private void saveAvatar(Avatar avatar, UserDTO userDTO) throws IOException {
        if (Objects.nonNull(avatar)) {
            LOG.info("Save user avatar with name :  --> {} / {}", userDTO.getLogin(), avatar.getExtension());
            avatarHelper.saveAvatar(avatar, userDTO.getLogin());
            userDTO.setAvatarExtension(avatar.getExtension());
        }
    }
}
