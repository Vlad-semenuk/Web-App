package com.epam.semeniuk.common.extractor.impl;

import com.epam.semeniuk.common.extractor.Extractor;
import com.epam.semeniuk.constant.Constant;
import com.epam.semeniuk.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

public class UserDTOExtractor implements Extractor<UserDTO> {

    @Override
    public UserDTO extractFromRequest(HttpServletRequest request) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(request.getParameter(Constant.LOGIN));
        userDTO.setFirstName(request.getParameter(Constant.FIRST_NAME));
        userDTO.setLastName(request.getParameter(Constant.LAST_NAME));
        userDTO.setEmail(request.getParameter(Constant.EMAIL));
        userDTO.setPassword(request.getParameter(Constant.PASSWORD));
        userDTO.setConfirmPassword(request.getParameter(Constant.CONFIRM_PASSWORD));
        userDTO.setSpam(checkboxExtract(request.getParameter(Constant.SPAM)));
        return userDTO;
    }

    private boolean checkboxExtract(String checkbox) {
        return "on".equals(checkbox);
    }

}
