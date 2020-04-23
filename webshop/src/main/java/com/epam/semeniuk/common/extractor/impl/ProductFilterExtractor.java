package com.epam.semeniuk.common.extractor.impl;

import com.epam.semeniuk.common.extractor.Extractor;
import com.epam.semeniuk.common.extractor.ExtractorUtils;
import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.enums.SqlOrderByValue;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epam.semeniuk.constant.Constant.*;


public class ProductFilterExtractor implements Extractor<ProductFilterDTO> {

    private static final Logger LOG = Logger.getLogger(ProductFilterExtractor.class);
    private static final String DEFAULT_SORT = "default";

    @Override
    public ProductFilterDTO extractFromRequest(HttpServletRequest request) throws IOException, ServletException {

        ProductFilterDTO productFilterDTO = (ProductFilterDTO) request.getSession().getAttribute(PRODUCT_FILTER);
        if (Objects.isNull(productFilterDTO)) {
            productFilterDTO = new ProductFilterDTO();
        } else {
            extractFilter(request, productFilterDTO);
        }
        return productFilterDTO;

    }

    private ProductFilterDTO extractFilter(HttpServletRequest request, ProductFilterDTO productFilterDTO) {
        String[] categories = request.getParameterValues(CATEGORY_FILTER);
        String[] manufacturers = request.getParameterValues(MANUFACTURER_FILTER);
        String nameFilter = request.getParameter(NAME_FILTER);
        Integer orderBy = ExtractorUtils.checkInteger(request.getParameter(ORDER_BY));

        if (Objects.nonNull(categories)) {
            productFilterDTO.setCategories(parseArray(categories));
        }
        if (Objects.nonNull(manufacturers)) {
            productFilterDTO.setManufacturers(parseArray(manufacturers));
        }
        Integer minPrice = ExtractorUtils.checkInteger(request.getParameter(MIN_PRICE_FILTER));
        Integer maxPrice = ExtractorUtils.checkInteger(request.getParameter(MAX_PRICE_FILTER));
        Integer productLimit = ExtractorUtils.checkInteger(request.getParameter(PRODUCT_LIMIT));

        Integer page = ExtractorUtils.checkInteger(request.getParameter(PAGE));

        if (Objects.nonNull(minPrice)) {
            productFilterDTO.setMinPrice(minPrice);
        }
        if (Objects.nonNull(maxPrice)) {
            productFilterDTO.setMaxPrice(maxPrice);
        }
        if (Objects.nonNull(productLimit)) {
            productFilterDTO.setProductLimit(productLimit);
        }
        if (Objects.nonNull(page) && page > 0) {
            productFilterDTO.setPage(page);
        }

        if (Objects.nonNull(nameFilter) && nameFilter.length() != 0) {
            productFilterDTO.setName(nameFilter);
        }
        if (Objects.nonNull(orderBy)) {
            productFilterDTO.setOrderBy(getOrderByValue(orderBy));
        }

        LOG.info(productFilterDTO);
        return productFilterDTO;
    }

    private List<Integer> parseArray(String[] categories) {
        List<Integer> categoryList = new ArrayList<>();
        for (String category : categories) {
            if (Objects.nonNull(ExtractorUtils.checkInteger(category))) {
                categoryList.add(Integer.parseInt(category));
            }
        }
        return categoryList;
    }

    private SqlOrderByValue getOrderByValue(int id){
        return SqlOrderByValue.getOrderByValue(id);
    }
}
