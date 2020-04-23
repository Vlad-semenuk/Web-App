package com.epam.semeniuk.servlet;

import com.epam.semeniuk.common.extractor.ExtractorUtils;
import com.epam.semeniuk.entity.Cart;
import com.epam.semeniuk.entity.Product;
import com.epam.semeniuk.exception.AppException;
import com.epam.semeniuk.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.*;
import static com.epam.semeniuk.constant.Paths.CART_PAGE;
import static com.epam.semeniuk.constant.Paths.ERROR_PAGE;

@WebServlet("/cartOperations")
public class CartOperationServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(CartOperationServlet.class);

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        performingOperation(req, resp);
    }

    private void performingOperation(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Cart cart = Cart.getCartFromRequest(req);

        try {
            if (Objects.nonNull(req.getParameter(OPERATION))) {
                String operation = req.getParameter(OPERATION);
                LOG.info("Operation name --> {}", operation);
                Integer productId = ExtractorUtils.checkInteger(req.getParameter(ID));
                Product product;
                switch (operation) {
                    case EDIT:
                        Integer quantity = ExtractorUtils.checkInteger(req.getParameter(QUANTITY));
                        if (Objects.nonNull(productId) && productService.isProductExist(productId) && Objects.nonNull(quantity)) {
                            product = productService.getProductById(productId);
                            cart.editProductQuantity(product, quantity);
                            break;
                        }
                        throw new AppException("Incorrect input data");
                    case REMOVE:
                        if (Objects.nonNull(productId) && productService.isProductExist(productId)) {
                            product = productService.getProductById(productId);
                            cart.deleteFromCart(product);
                            break;
                        }
                        throw new AppException("Incorrect input data");
                    case CLEAR:
                        cart.clearCart();
                        break;
                    default:
                        throw new AppException("Incorrect operation --> " + operation);
                }
            }
        } catch (AppException e) {
            LOG.error(e);
            req.getSession().setAttribute(ERRORS, e);
            resp.sendRedirect(ERROR_PAGE);
        }

        int quantity = cart.getQuantityProduct();
        int totalPrice = cart.getTotalPrice();

        req.getSession().setAttribute(CART, cart);
        req.getSession().setAttribute(QUANTITY, quantity);
        req.getSession().setAttribute(TOTAL_PRICE, totalPrice);

        req.getRequestDispatcher(CART_PAGE).forward(req, resp);
    }

}
