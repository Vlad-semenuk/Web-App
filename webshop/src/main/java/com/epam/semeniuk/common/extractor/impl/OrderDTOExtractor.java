package com.epam.semeniuk.common.extractor.impl;

import com.epam.semeniuk.common.extractor.Extractor;
import com.epam.semeniuk.common.extractor.ExtractorUtils;
import com.epam.semeniuk.dto.OrderDTO;

import javax.servlet.http.HttpServletRequest;

public class OrderDTOExtractor implements Extractor<OrderDTO> {

    private static final String USER_ADDRESS = "user_address";
    private static final String USER_CARD = "user_card";
    private static final String DELIVERY_METHOD = "delivery-method";
    private static final String PAYMENT_METHOD = "payment-method";

    @Override
    public OrderDTO extractFromRequest(HttpServletRequest request) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAddress(request.getParameter(USER_ADDRESS));
        orderDTO.setCard(request.getParameter(USER_CARD));
        orderDTO.setDeliveryMethod(ExtractorUtils.checkInteger(request.getParameter(DELIVERY_METHOD)));
        orderDTO.setPaymentMethod(ExtractorUtils.checkInteger(request.getParameter(PAYMENT_METHOD)));
        return orderDTO;
    }
}

