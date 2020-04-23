package com.epam.semeniuk.common.validator.impl;

import com.epam.semeniuk.avatar.Avatar;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.*;

public class AvatarValidator  {
    private static final Logger LOG = Logger.getLogger(AvatarValidator.class);

    public Map<String, String> validate(Avatar avatar) {
        LOG.debug("AvatarValidator starts.");
        Map<String, String> errors = new HashMap<>();
        if (Objects.nonNull(avatar)) {
            validateExtension(errors, avatar.getExtension());
        }
        LOG.debug("AvatarValidator finished.");
        return errors;
    }

    private void validateExtension(Map<String, String> errors, String image) {
        if (!image.matches(IMAGE_PATTERN)) {
            errors.put(AVATAR_ERROR_MSG_PATH, AVATAR_EXTENSION_ERROR_MESSAGE);
        }
    }
}
