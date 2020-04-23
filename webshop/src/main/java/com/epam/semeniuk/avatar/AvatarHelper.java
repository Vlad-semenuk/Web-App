package com.epam.semeniuk.avatar;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class AvatarHelper {
    private static final Logger LOG = LogManager.getLogger(AvatarHelper.class);

    private final File AVATAR_FOLDER;

    public AvatarHelper(File folder) {
        this.AVATAR_FOLDER = folder;
    }

    public void saveAvatar(Avatar avatar, String newAvatarName) {
        InputStream imageStream = new ByteArrayInputStream(avatar.getImage());
        File avatarFileDestination = new File(AVATAR_FOLDER, newAvatarName + "." + avatar.getExtension());
        try {
            Files.copy(imageStream, avatarFileDestination.toPath());
        } catch (IOException e){
            LOG.error("Can not save avatar", e);
        }
        LOG.info("Save avatar to path : {} --> {}", avatar.getFileName(), avatarFileDestination);


    }

    public boolean isImageExist(String avatarFileName) {
        File avatarFile = new File(AVATAR_FOLDER, avatarFileName);
        return avatarFile.exists();
    }

    public BufferedImage getAvatar(String avatarFileName) throws IOException {
        File avatarFile = new File(AVATAR_FOLDER, avatarFileName);
        LOG.debug("Read image from Avatar file  : avatarFile --> {}", avatarFile);
        return ImageIO.read(avatarFile);
    }
}
