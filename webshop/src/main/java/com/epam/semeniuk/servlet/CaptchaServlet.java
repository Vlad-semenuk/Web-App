package com.epam.semeniuk.servlet;

import com.epam.semeniuk.captcha.Captcha;
import com.epam.semeniuk.captcha.handler.CaptchaHandler;
import com.epam.semeniuk.captcha.helper.CaptchaHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.semeniuk.constant.Constant.CAPTCHA_HANDLER;
import static com.epam.semeniuk.constant.Constant.CAPTCHA_SERVICE;
import static com.epam.semeniuk.constant.Paths.REGISTER_PAGE;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(CaptchaServlet.class);
    private static final String CAPTCHA_IMAGE = "captchaImage";
    private static final String CAPTCHA = "captcha";

    private CaptchaHelper captchaHelper;
    private CaptchaHandler captchaHandler;

    @Override
    public void init(){
        captchaHelper = (CaptchaHelper) getServletContext().getAttribute(CAPTCHA_SERVICE);
        captchaHandler = (CaptchaHandler) getServletContext().getAttribute(CAPTCHA_HANDLER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Captcha captcha = captchaHelper.generateCaptcha();
        LOG.info("Generate captcha: --> {}", captcha);
        req.getSession().setAttribute(CAPTCHA, captcha);
        captchaHandler.saveCaptcha(req, resp, captcha);
        String captchaImage = captchaHelper.drawCaptcha(captcha);
        req.setAttribute(CAPTCHA_IMAGE, captchaImage);
        LOG.info("Set the session attribute: --> {}" ,!captchaImage.isEmpty());

        LOG.debug(String.format("Go to forward address --> {}", REGISTER_PAGE));
        getServletContext().getRequestDispatcher(REGISTER_PAGE).forward(req, resp);
    }
}
