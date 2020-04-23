package com.epam.semeniuk.servlet;

import com.epam.semeniuk.avatar.AvatarHelper;
import com.epam.semeniuk.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;

import static com.epam.semeniuk.constant.Constant.AVATAR_HELPER;

@WebServlet("/avatar")
public class AvatarServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(AvatarServlet.class);
    private static final String USER = "user";
    private static final int ERROR_401 = 401;

    private AvatarHelper avatarHelper;

    @Override
    public void init() {
        avatarHelper = (AvatarHelper) getServletContext().getAttribute(AVATAR_HELPER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute(USER);
        if (user != null) {
            String imageName = user.getLogin() + "." + user.getAvatarExtension();
            LOG.info("Avatar name --> {}", imageName);
            RenderedImage renderedImage;
            try {
                if (avatarHelper.isImageExist(imageName)) {
                    renderedImage = avatarHelper.getAvatar(imageName);
                    ImageIO.write(renderedImage, user.getAvatarExtension(), resp.getOutputStream());
                }
            } catch (IOException e) {
                LOG.error("Can not load avatar", e);
            }
        } else {
            resp.sendError(ERROR_401, "Access is denied");
        }
    }
}
