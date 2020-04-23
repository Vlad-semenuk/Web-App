package com.epam.semeniuk.servlet;

import com.epam.semeniuk.common.extractor.impl.OrderDTOExtractor;
import com.epam.semeniuk.common.validator.impl.OrderValidator;
import com.epam.semeniuk.dto.OrderDTO;
import com.epam.semeniuk.entity.Cart;
import com.epam.semeniuk.entity.Order;
import com.epam.semeniuk.entity.User;
import com.epam.semeniuk.enums.Delivery;
import com.epam.semeniuk.enums.OrderStatus;
import com.epam.semeniuk.enums.Payment;
import com.epam.semeniuk.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.*;
import static com.epam.semeniuk.constant.Paths.CREATE_ORDER_PAGE;
import static com.epam.semeniuk.constant.Paths.MAIN_PAGE;


@WebServlet("/createOrder")
public class OrderServlet extends HttpServlet {

    private static final String CREATE_ORDER_SERVLET = "/createOrder";
    private static final String REGISTER_SERVLET = "/register";
    private OrderService orderService;

    @Override
    public void init() {
        orderService = (OrderService) getServletContext().getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_ORDER_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute(CART);
        User user = (User) req.getSession().getAttribute(USER);
        if (Objects.nonNull(user)) {
            OrderDTO orderDTO = new OrderDTOExtractor().extractFromRequest(req);
            Map<String, String> errors = new OrderValidator().validate(orderDTO);
            if (errors.isEmpty()) {
                Order order = createNewOrder(user, orderDTO);
                orderService.saveOrder(cart, order);
                req.getSession().removeAttribute(CART);
                req.getSession().removeAttribute(QUANTITY);
                resp.sendRedirect(MAIN_PAGE);
            } else {
                req.getSession().setAttribute(ERRORS, errors);
                resp.sendRedirect(CREATE_ORDER_SERVLET);
            }
        } else {
            resp.sendRedirect(REGISTER_SERVLET);
        }
    }

    private Order createNewOrder(User user, OrderDTO orderDTO) {
        Order order = new Order();
        order.setLocalDate(LocalDate.now());
        order.setUserLogin(user.getLogin());
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setUserAddress(orderDTO.getAddress());
        order.setUserCard(orderDTO.getCard());
        order.setDeliveryMethod(Delivery.getDeliveryMethodById(orderDTO.getDeliveryMethod()));
        order.setPaymentMethod(Payment.getPaymentMethodById(orderDTO.getPaymentMethod()));
        return order;
    }
}
