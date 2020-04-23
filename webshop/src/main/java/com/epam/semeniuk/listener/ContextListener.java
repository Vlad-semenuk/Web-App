package com.epam.semeniuk.listener;


import com.epam.semeniuk.captcha.Captcha;
import com.epam.semeniuk.captcha.handler.*;
import com.epam.semeniuk.captcha.helper.CaptchaHelper;
import com.epam.semeniuk.common.validator.impl.AvatarValidator;
import com.epam.semeniuk.common.validator.impl.CaptchaValidator;
import com.epam.semeniuk.common.validator.impl.LoginValidator;
import com.epam.semeniuk.common.validator.impl.UserValidator;
import com.epam.semeniuk.dao.UserDAO;
import com.epam.semeniuk.dao.connection.ConnectionPool;
import com.epam.semeniuk.dao.postgresql.PostgresqlOrderDAO;
import com.epam.semeniuk.dao.postgresql.PostgresqlUserDAO;
import com.epam.semeniuk.dao.transaction.TransactionManager;
import com.epam.semeniuk.entity.Product;
import com.epam.semeniuk.exception.AppException;

import com.epam.semeniuk.repository.CategoryRepository;
import com.epam.semeniuk.repository.ManufacturerRepository;
import com.epam.semeniuk.repository.impl.PostgresqlCategoryRepository;
import com.epam.semeniuk.repository.impl.PostgresqlManufacturerRepository;
import com.epam.semeniuk.repository.impl.PostgresqlProductRepository;
import com.epam.semeniuk.repository.ItemRepository;
import com.epam.semeniuk.service.*;
import com.epam.semeniuk.service.dbService.*;


import com.epam.semeniuk.avatar.AvatarHelper;
import com.github.cage.Cage;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.epam.semeniuk.constant.Constant.*;


@WebListener("/*")
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    private static final String AVATAR_LOCATION = "avatar.location";
    private static final String PASS = "pass";
    private static final String USER = "user";
    private static final String URL = "url";
    private final long LIFE_TIME = 60 * 1000;
    private static final String SESSION_CAPTCHA_HANDLER = "sessionCaptchaHandler";
    private static final String APP_CAPTCHA_HANDLER = "appCaptchaHandler";
    private static final String COOKIE_CAPTCHA_HANDLER = "cookieCaptchaHandler";
    private static final String CAPTCHA_HANDLER_TYPE = "captchaHandler";

    private Cage cage;
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        cage = new Cage();
        ServletContext servletContext = sce.getServletContext();
        try {
        initDataSource(servletContext);
        initService(servletContext);
        initValidators(servletContext);

            initHandler(servletContext);
        } catch (AppException exception) {
            LOG.error(exception);
        }

    }

    private void initService(ServletContext servletContext) {
        TransactionManager transactionManager = new TransactionManager(dataSource);
        UserDAO userDAO = new PostgresqlUserDAO();

        ItemRepository<Product> productRepository = new PostgresqlProductRepository();
        CategoryRepository categoryRepository = new PostgresqlCategoryRepository();
        ManufacturerRepository manufacturerRepository = new PostgresqlManufacturerRepository();

        OrderService orderService = new DBOrderService(new PostgresqlOrderDAO(), transactionManager);
        UserService userService = new DBUserService(transactionManager, userDAO);
        AvatarHelper avatarService = new AvatarHelper(new File(servletContext.getInitParameter(AVATAR_LOCATION)));
        CaptchaHelper captchaHelper = new CaptchaHelper(cage, LIFE_TIME);
        ProductService productService = new DBProductService(transactionManager, productRepository);
        ManufacturerService manufacturerService = new DBManufacturerService(transactionManager, manufacturerRepository);
        CategoryService categoryService = new DBCategoryService(transactionManager, categoryRepository);

        servletContext.setAttribute(AVATAR_HELPER, avatarService);
        servletContext.setAttribute(ORDER_SERVICE, orderService);
        servletContext.setAttribute(PRODUCT_SERVICE, productService);
        servletContext.setAttribute(CATEGORY_SERVICE, categoryService);
        servletContext.setAttribute(MANUFACTURER_SERVICE, manufacturerService);
        servletContext.setAttribute(USER_SERVICE, userService);
        servletContext.setAttribute(CAPTCHA_SERVICE, captchaHelper);
    }


    private void initValidators(ServletContext servletContext) {
        UserValidator formValidator = new UserValidator();
        CaptchaValidator captchaValidator = new CaptchaValidator();
        AvatarValidator avatarValidator = new AvatarValidator();
        LoginValidator loginValidator = new LoginValidator();

        servletContext.setAttribute(LOGIN_VALIDATOR, loginValidator);
        servletContext.setAttribute(AVATAR_VALIDATOR, avatarValidator);
        servletContext.setAttribute(CAPTCHA_VALIDATOR, captchaValidator);
        servletContext.setAttribute(USER_VALIDATE, formValidator);
    }

    private void initDataSource(ServletContext servletContext) throws AppException {
        String url = servletContext.getInitParameter(URL);
        String user = servletContext.getInitParameter(USER);
        String pass  = servletContext.getInitParameter(PASS);
        if (user == null || url == null || pass == null){
            throw new AppException("Init param not found");
        }
        try {
            dataSource = ConnectionPool.createDataSource(url, user, pass);
        } catch (SQLException e) {
            LOG.error("DataSource was not initialize.", e);
        }
    }

    private void initHandler(ServletContext servletContext) throws AppException {
        Map<String, CaptchaHandler> captchaHandlerMap = new HashMap<>();
        ConcurrentMap<UUID, Captcha> captchaMap = new ConcurrentHashMap<>();
        captchaHandlerMap.put(SESSION_CAPTCHA_HANDLER, new SessionCaptchaHandler());
        captchaHandlerMap.put(COOKIE_CAPTCHA_HANDLER, new CookieCaptchaHandler(captchaMap));
        captchaHandlerMap.put(APP_CAPTCHA_HANDLER, new ApplicationCaptchaHandler(captchaMap));
        String handlerName = servletContext.getInitParameter(CAPTCHA_HANDLER_TYPE);

        if (!captchaHandlerMap.containsKey(handlerName)){
            throw new AppException("Handler not specified");
        }
        CaptchaHandler captchaHandler = captchaHandlerMap.get(handlerName);
        servletContext.setAttribute(CAPTCHA_HANDLER, captchaHandler);
        if (captchaHandler instanceof MappedCaptchaHandler) {
            new CaptchaCleaner(captchaMap, LIFE_TIME).start();
        }
    }
}
