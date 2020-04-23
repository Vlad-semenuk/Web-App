package com.epam.semeniuk.common.extractor.impl;

import com.epam.semeniuk.avatar.Avatar;
import com.epam.semeniuk.common.extractor.Extractor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


public class AvatarExtractor implements Extractor<Avatar> {
    private static final Logger LOG = LogManager.getLogger(AvatarExtractor.class);

    private static final String AVATAR_PARAMETER = "avatar";

    @Override
    public Avatar extractFromRequest(HttpServletRequest request) throws IOException, ServletException {
        LOG.debug("AvatarExtractor starts.");

        Avatar avatar = null;
        Part part = request.getPart(AVATAR_PARAMETER);
        LOG.info("Get the request Part: " + AVATAR_PARAMETER + " --> " + part.getName());
        if (part.getSize() != 0) {
            try (InputStream fileContent = part.getInputStream()) {
                String inputAvatarFileName = getSubmittedFileName(part);
                String fileExtension = FilenameUtils.getExtension(inputAvatarFileName);
                byte[] bytes = IOUtils.toByteArray(fileContent);
                LOG.info("Get Submitted FileName: inputAvatarFileName --> {}", inputAvatarFileName);

                avatar = new Avatar();
                avatar.setFileName(inputAvatarFileName);
                avatar.setExtension(fileExtension);
                avatar.setImage(bytes);
            }
        }
        LOG.info("Extracted avatar: --> {}",  avatar);

        LOG.debug("AvatarExtractor finished.");
        return avatar;
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1)
                        .substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
