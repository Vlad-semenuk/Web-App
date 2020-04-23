package com.epam.semeniuk.servlet;

import com.epam.semeniuk.common.extractor.ExtractorUtils;
import com.epam.semeniuk.entity.Cart;
import com.epam.semeniuk.entity.Product;
import com.epam.semeniuk.service.ProductService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.*;
import static com.epam.semeniuk.constant.Paths.ERROR_PAGE;

@WebServlet("/add")
public class AddToCartByAjaxServlet extends HttpServlet {

    private static final String ID = "id";
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = Cart.getCartFromRequest(req);

        JSONObject jsonObject = new JSONObject();

        Integer productId = ExtractorUtils.checkInteger(req.getParameter(ID));
        if (Objects.nonNull(productId) && productService.isProductExist(productId)) {
            Product product = productService.getProductById(productId);
            cart.addToCart(product, 1);

            int quantity = cart.getQuantityProduct();
            int totalPrice = cart.getTotalPrice();

            jsonObject.put(QUANTITY, quantity);

            PrintWriter printWriter = resp.getWriter();
            printWriter.print(jsonObject);

            req.getSession().setAttribute(TOTAL_PRICE, totalPrice);
            req.getSession().setAttribute(CART, cart);
            req.getSession().setAttribute(QUANTITY, quantity);

        } else {
            req.getSession().setAttribute(ERRORS, "Can not adding product to cart");
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
