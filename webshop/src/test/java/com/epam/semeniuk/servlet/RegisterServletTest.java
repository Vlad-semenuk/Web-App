package com.epam.semeniuk.servlet;


import com.epam.semeniuk.avatar.Avatar;
import com.epam.semeniuk.avatar.AvatarHelper;
import com.epam.semeniuk.captcha.Captcha;
import com.epam.semeniuk.captcha.handler.CaptchaHandler;
import com.epam.semeniuk.common.extractor.Extractor;
import com.epam.semeniuk.common.validator.impl.AvatarValidator;
import com.epam.semeniuk.common.validator.impl.CaptchaValidator;
import com.epam.semeniuk.common.validator.impl.UserValidator;
import com.epam.semeniuk.dto.UserDTO;
import com.epam.semeniuk.entity.User;
import com.epam.semeniuk.enums.Role;
import com.epam.semeniuk.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServletTest {

    private static final String LOGIN = "login";
    private static final String FIRST_NAME_VALUE = "Fname";
    private static final String LAST_NAME_VALUE = "Lname";
    private static final String EMAIL_VALUE = "vlad@gmail.com";
    private static final String PASSWORD_VALUE = "Qw12#";
    private static final String AVATAR_PARAMETER = "avatar";

    private static final String ERRORS = "errors";
    private static final String REGISTRATION_FORM_REQUEST_EXTRACTOR = "registrationFormExtractor";
    private static final String CAPTCHA_REQUEST_EXTRACTOR = "captchaExtractor";
    private static final String AVATAR_REQUEST_EXTRACTOR = "avatarExtractor";

    private final String CAPTCHA_SERVLET = "/captcha";
    private final String MAIN_PAGE = "/";
    private Map<String, String> mapWithErrors;

    @Mock
    private Extractor<UserDTO> registrationFormExtractorMock;
    @Mock
    private Extractor<Captcha> captchaExtractorMock;
    @Mock
    private Extractor<Avatar> avatarExtractor;
    @Mock
    private Captcha captchaMock;
    @Mock
    private Avatar avatarMock;
    @Mock
    private UserDTO userDTO;
    @Mock
    private User userMock;
    @Mock
    private CaptchaValidator captchaValidator;
    @Mock
    private UserValidator userValidator;
    @Mock
    private AvatarValidator avatarValidator;

    @Mock
    private CaptchaHandler captchaHandler;
    @Mock
    private UserService userService;
    @Mock
    private AvatarHelper avatarHelper;
    @Mock
    private RequestDispatcher requestDispatcherMock;
    @Mock
    private HttpServletResponse respMock;
    @Mock
    private HttpServletRequest reqMock;
    @Mock
    private HttpSession sessionMock;
    @Mock
    private ServletContext servletContextMock;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private Part part;

    @InjectMocks
    private RegisterServlet servlet = new RegisterServlet();

    @Before
    public void setUp() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(LOGIN, LAST_NAME_VALUE, FIRST_NAME_VALUE, EMAIL_VALUE, PASSWORD_VALUE, true, "jpg", Role.USER));
        Optional<User> optional = users.stream().findFirst();
        when(servletContextMock.getAttribute(REGISTRATION_FORM_REQUEST_EXTRACTOR)).thenReturn(registrationFormExtractorMock);
        when(servletContextMock.getAttribute(CAPTCHA_REQUEST_EXTRACTOR)).thenReturn(captchaExtractorMock);
        when(servletContextMock.getAttribute(AVATAR_REQUEST_EXTRACTOR)).thenReturn(avatarExtractor);

        when(servletContextMock.getRequestDispatcher(CAPTCHA_SERVLET)).thenReturn(requestDispatcherMock);
        when(servletConfig.getServletContext()).thenReturn(servletContextMock);

        when(registrationFormExtractorMock.extractFromRequest(reqMock)).thenReturn(userDTO);
        when(captchaExtractorMock.extractFromRequest(reqMock)).thenReturn(captchaMock);
        when(avatarExtractor.extractFromRequest(reqMock)).thenReturn(avatarMock);
        when(reqMock.getPart(AVATAR_PARAMETER)).thenReturn(part);
        when(reqMock.getSession()).thenReturn(sessionMock);
        when(userMock.getEmail()).thenReturn(EMAIL_VALUE);

        mapWithErrors = new HashMap<>();
        when(reqMock.getContextPath()).thenReturn(MAIN_PAGE);
        when(userService.findUserByLogin(userDTO.getLogin())).thenReturn(optional);
        when(captchaValidator.validate(any(Captcha.class), any(Captcha.class))).thenReturn(Collections.emptyMap());
        when(userValidator.validate(userDTO, userService)).thenReturn(Collections.emptyMap());
        when(avatarValidator.validate(any(Avatar.class))).thenReturn(Collections.emptyMap());

    }

    @Test
    public void shouldRemoveErrorsMessagesFromSessionAndForwardOnCaptchaServlet() throws Exception {
        servlet.doGet(reqMock, respMock);
        verify(servletContextMock, times(1)).getRequestDispatcher(CAPTCHA_SERVLET);
        verify(requestDispatcherMock, times(1)).forward(reqMock, respMock);
    }

    @Test
    public void shouldSendRedirectOnMainPageIfAllIsValid() throws Exception {
        servlet.doPost(reqMock, respMock);
        verify(respMock, times(1)).sendRedirect(MAIN_PAGE);
    }

    @Test
    public void shouldAddCaptchaErrorsMessagesAndRedirectOnCaptchaServletIfCaptchaIsNotValid() throws Exception {
        mapWithErrors.put("captchaError", "errorMsg");
        doReturn(mapWithErrors).when(captchaValidator).validate(any(Captcha.class), any(Captcha.class));
        servlet.doPost(reqMock, respMock);
        verify(sessionMock).setAttribute(ERRORS, mapWithErrors);
        verify(respMock, times(1)).sendRedirect(CAPTCHA_SERVLET);
    }

    @Test
    public void shouldAddFormErrorsMessagesAndRedirectOnCaptchaServletRegistrationIfFormIsNotValid() throws Exception {
        mapWithErrors.put("formError", "errorMsg");
        doReturn(mapWithErrors).when(captchaValidator).validate(captchaMock, captchaMock);
        servlet.doPost(reqMock, respMock);
        verify(respMock, times(1)).sendRedirect(MAIN_PAGE);
    }
}