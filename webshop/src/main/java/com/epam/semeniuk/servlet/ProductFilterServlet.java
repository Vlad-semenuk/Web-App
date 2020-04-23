package com.epam.semeniuk.servlet;

import com.epam.semeniuk.common.extractor.impl.ProductFilterExtractor;
import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.entity.Category;
import com.epam.semeniuk.entity.Manufacturer;
import com.epam.semeniuk.entity.Product;
import com.epam.semeniuk.service.CategoryService;
import com.epam.semeniuk.service.ManufacturerService;
import com.epam.semeniuk.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.semeniuk.constant.Constant.*;
import static com.epam.semeniuk.constant.Paths.PRODUCTS_PAGE;


@WebServlet("/products")
public class ProductFilterServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ProductFilterServlet.class);
    private static final String PRODUCTS = "products";
    private static final String MAKERS = "makers";
    private static final String CATEGORIES = "categories";
    private static final String PRODUCT_COUNT = "productCount";
    private ProductService productService;
    private ManufacturerService manufacturerService;
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductFilterDTO productFilterDTO = new ProductFilterExtractor().extractFromRequest(req);
        List<Product> products = productService.getAllProductsFromSqlQuery(productFilterDTO);
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturer();
        List<Category> categories = categoryService.getAllCategories();
        int productCount = productService.getCountProductsFromSqlQuery(productFilterDTO);
        LOG.info("Product list --> {}", products);

        req.getSession().setAttribute(PRODUCT_FILTER, productFilterDTO);
        req.getSession().setAttribute(PRODUCTS, products);
        req.getSession().setAttribute(MAKERS, manufacturers);
        req.getSession().setAttribute(CATEGORIES, categories);
        req.getSession().setAttribute(PRODUCT_COUNT, productCount);

        getServletContext().getRequestDispatcher(PRODUCTS_PAGE).forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
        categoryService = (CategoryService) getServletContext().getAttribute(CATEGORY_SERVICE);
        manufacturerService = (ManufacturerService) getServletContext().getAttribute(MANUFACTURER_SERVICE);
    }
}
