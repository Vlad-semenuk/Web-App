package com.epam.semeniuk.common.validator.impl;

import com.epam.semeniuk.common.extractor.ExtractorUtils;
import com.epam.semeniuk.dto.OrderDTO;
import com.epam.semeniuk.enums.Delivery;
import com.epam.semeniuk.enums.Payment;
import com.epam.semeniuk.service.dbService.DBOrderService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.*;

public class OrderValidator {

    private static final String CARD_NUMBER_REGEX = "\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}";

    public Map<String, String> validate(OrderDTO orderDTO){
        Map<String, String> error = new HashMap<>();
        if (Objects.isNull(orderDTO.getAddress())){
            error.put(ADDRESS_ERROR_PATH, ADDRESS_ERROR_MSG);
        }
        if (!isDeliveryValid(orderDTO.getDeliveryMethod()) || !isPaymentValid(orderDTO.getPaymentMethod())){
            error.put(CHOOSE_ERROR_PATH, CHOOSE_ERROR_MSG);
        }
        if (!isCardNumberValid(orderDTO.getCard())){
            error.put(CARD_NUMBER_ERROR_PATH, CARD_NUMBER_ERROR_MSG);
        }
        return error;
    }

    private boolean isCardNumberValid(String cardNumber){
        return cardNumber.matches(CARD_NUMBER_REGEX);
    }

    private boolean isDeliveryValid(Integer deliveryId){
        if (Objects.nonNull(deliveryId)){
            return deliveryId > 0 && deliveryId <= Delivery.values().length;
        }
        return false;
    }

    private boolean isPaymentValid(Integer paymentId){
        if (Objects.nonNull(paymentId)){
            return paymentId > 0 && paymentId <= Payment.values().length;
        }
        return false;
    }
}
