package com.epam.semeniuk.constant;


public class Constant {

    private Constant() {
    }
    public static final String USER = "user";
    public static final String LOGIN = "login";
    public static final String FIRST_NAME = "first-name";
    public static final String LAST_NAME = "last-name";
    public static final String PASSWORD = "password";
    public static final String CONFIRM_PASSWORD = "confirm-password";
    public static final String SPAM = "spam";
    public static final String EMAIL = "email";
    public static final String ERRORS = "errors";

    public static final String AVATAR_VALIDATOR = "avatarValidator";
    public static final String USER_VALIDATE = "userValidate";
    public static final String CAPTCHA_VALIDATOR = "captchaValidator";
    public static final String LOGIN_VALIDATOR = "loginValidator";

    public static final String CAPTCHA_HANDLER = "captchaHandler";
    //filter
    public static final String CATEGORY_FILTER = "category-checkbox";
    public static final String MANUFACTURER_FILTER = "maker-checkbox";
    public static final String NAME_FILTER = "name-filter";
    public static final String MIN_PRICE_FILTER = "min-price";
    public static final String MAX_PRICE_FILTER = "max-price";
    public static final String ORDER_BY = "order-by";
    public static final String PRODUCT_LIMIT = "product-limit";
    public static final String PRODUCT_FILTER = "filter";
    public static final String PAGE = "page";

    public static final String LOCALE = "locale";

    //service
    public static final String USER_SERVICE = "userService";
    public static final String ORDER_SERVICE = "orderService";
    public static final String CAPTCHA_SERVICE = "captchaService";
    public static final String AVATAR_HELPER = "avatarHelper";
    public static final String PRODUCT_SERVICE = "productService";
    public static final String CATEGORY_SERVICE = "categoryService";
    public static final String MANUFACTURER_SERVICE = "manufacturerService";


    //validate constant
    public static final String IMAGE_PATTERN = "((jpg|png|gif|bmp))$";
    public static final String LOGIN_REGEXP = "^[a-zA-z][a-zA-Z1-9]{3,16}$";
    public static final String NAME_REGEXP = "^[a-zA-Z ]{2,16}$";
    public static final String EMAIL_REGEXP = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEXP = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{4,}";
    public static final String NUMBER_REGEXP = "\\d+";

    public final static String LOGIN_ERROR_MSG_PATH = "login-error";
    public final static String FIRST_NAME_ERROR_MSG_PATH = "firs-name-error";
    public final static String LAST_NAME_ERROR_MSG_PATH = "last-name-error";
    public final static String EMAIL_ERROR_MSG_PATH = "email-error";
    public final static String PASSWORD_ERROR_MSG_PATH = "password-error";
    public final static String CONFIRM_PASSWORD_ERROR_MSG_PATH = "confirm-password-error";
    public final static String CHECKBOX_ERROR_MSG_PATH = "checkbox-error";
    public final static String USER_CREATE_ERROR_PATH = "user-create-error";
    public final static String USER_NOT_FOUND_ERROR_PATH = "user-found-error";
    public static final String ADDRESS_ERROR_PATH = "address-error";
    public static final String CARD_NUMBER_ERROR_PATH = "card-error";
    public static final String CHOOSE_ERROR_PATH = "choose-error";

    public final static String USER_ALREADY_EXIST = "*User login already exist";
    public final static String USER_NOT_FOUND = "*Incorrect login or password";
    public final static String LOGIN_ERROR_MSG = "*Login must have alphabet characters only and min 4 symbol!";
    public final static String FIRST_NAME_ERROR_MSG = "*First name must have alphabet characters only!";
    public final static String LAST_NAME_ERROR_MSG = "*Last name must have alphabet characters only!";
    public final static String EMAIL_ERROR_MSG = "*You have entered an invalid email address! Please enter valid email address, like: hello@gmail.com";
    public final static String PASSWORD_ERROR_MSG = " *Password should contains more then 4 symbol and should have uppercase symbol!";
    public final static String CONFIRM_PASSWORD_ERROR_MSG = "*Password confirm should not be empty / should be equals with Password";
    public final static String EMPTY_CHECKBOX_ERROR_MSG = "Wrong checkbox !";
    public static final String CAPTCHA_ERROR_PATH = "captcha-error";
    public static final String CAPTCHA_EMPTY_ERROR_MSG = "Captcha can not be empty!";
    public static final String CAPTCHA_WRONG_ERROR_MSG = "Wrong value of captcha!";
    public static final String CAPTCHA_TIMEOUT_ERROR_MSG = "Registration timed out!";
    public static final String ADDRESS_ERROR_MSG = "Address should not be empty";
    public static final String CARD_NUMBER_ERROR_MSG = "Incorrect card number";
    public static final String CHOOSE_ERROR_MSG = "Choose this field";

    public static final String AVATAR_EXTENSION_ERROR_MESSAGE = "Wrong image extension!";
    public static final String AVATAR_ERROR_MSG_PATH = "avatar-error-msg";

    //cart
    public static final String CART = "cart";
    public static final String EDIT = "edit";
    public static final String REMOVE = "remove";
    public static final String CLEAR = "clear";
    public static final String ID = "id";
    public static final String OPERATION = "operation";
    public static final String QUANTITY = "quantity";
    public static final String TOTAL_PRICE = "totalPrice";

    //locale filter
    public static final String LANG = "lang";
    public static final String RU = "ru";
    public static final String EN = "en";
    public static final String LOCALE_EN = "localeEN";
    public static final String LOCALE_RU = "localeRU";
    public static final String SESSION = "session";
    public static final String COOKIE = "cookie";
    public static final String LOCALE_STORAGE = "localeStorage";
    public static final String COOKIE_LIFE_TIME = "cookieLifeTime";
    public static final String DEFAULT_LOCALE = "defaultLocale";
}
