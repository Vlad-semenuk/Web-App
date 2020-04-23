package com.epam.semeniuk.querybuilder;


import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.querybuilder.builder.PostgresqlSelectBuilder;
import com.epam.semeniuk.querybuilder.builder.SelectBuilder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class GetFilteredProductSqlQuery {

    private static final String PRODUCT_CATEGORY = "product_category = ";
    private static final String PRODUCT_MANUFACTURER = "product_manufacturer = ";
    private static final String PRODUCT_PRICE = "product_price ";
    private static final String PRODUCT_NAME = "product_name";
    private static final String START_PART_QUERY = "SELECT * FROM products p  INNER JOIN categories c on p.product_category = c.category_id INNER JOIN manufactorers m on p.product_manufacturer = m.manufacturer_id";
    private static final String START_PART_COUNT_QUERY = "SELECT COUNT(*) FROM products p  INNER JOIN categories c on p.product_category = c.category_id INNER JOIN manufactorers m on p.product_manufacturer = m.manufacturer_id";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String MIN_PRICE_CONDITION = "> ";
    private static final String MAX_PRICE_CONDITION = "< ";
    private static final String NAME_CONDITION = " = ";

    private SelectBuilder selectBuilder;

    public GetFilteredProductSqlQuery() {
        selectBuilder = new PostgresqlSelectBuilder();
    }

    public String buildAllProductQuery(ProductFilterDTO filterDTO) {
        StringBuilder query = new StringBuilder(START_PART_QUERY);

        query.append(buildQuery(filterDTO));

        if (Objects.nonNull(filterDTO.getOrderBy())) {
            query.append(selectBuilder.buildOrderByCondition(filterDTO.getOrderBy().getColumn()));
        }

        if (Objects.nonNull(filterDTO.getProductLimit()) && Objects.nonNull(filterDTO.getPage())) {
            query.append(selectBuilder.buildLimitOffsetCondition(filterDTO.getProductLimit(), filterDTO.getPage()));
        }
        query.append(";");

        return query.toString();
    }

    public String buildCountProductsQuery(ProductFilterDTO filterDTO) {
        StringBuilder query = new StringBuilder(START_PART_COUNT_QUERY);
        query.append(buildQuery(filterDTO)).append(";");
        return query.toString();
    }


    private String buildQuery(ProductFilterDTO filterDTO) {
        StringBuilder query = new StringBuilder();
        boolean first = true;

        if (Objects.nonNull(filterDTO.getMinPrice())) {
            query.append(buildSeparator(first));
            query.append(selectBuilder.buildEqualCondition(filterDTO.getMinPrice(), MIN_PRICE_CONDITION, PRODUCT_PRICE));
            first = false;
        }

        if (Objects.nonNull(filterDTO.getMaxPrice())) {
            query.append(buildSeparator(first));
            query.append(selectBuilder.buildEqualCondition(filterDTO.getMaxPrice(), MAX_PRICE_CONDITION, PRODUCT_PRICE));
            first = false;
        }

        if (Objects.nonNull(filterDTO.getName())) {
            query.append(buildSeparator(first));
            query.append(selectBuilder.buildEqualCondition(filterDTO.getName(), NAME_CONDITION, PRODUCT_NAME));
            first = false;
        }

        if (Objects.nonNull(filterDTO.getCategories())) {
            query.append(buildSeparator(first));
            query.append(selectBuilder.buildEqualAllConditions(filterDTO.getCategories(), PRODUCT_CATEGORY));
            first = false;
        }

        if (Objects.nonNull(filterDTO.getManufacturers())) {
            query.append(buildSeparator(first));
            query.append(selectBuilder.buildEqualAllConditions(filterDTO.getManufacturers(), PRODUCT_MANUFACTURER));
        }
        return query.toString();
    }

    private String buildSeparator(boolean first) {
        return first ? WHERE : AND;
    }

    public void setPreparedStatementParameters(PreparedStatement ps) throws SQLException {
        int i = 1;
        List<Object> objects = selectBuilder.getParameters();
        for (Object param : objects) {
            if (param instanceof Integer) {
                ps.setInt(i++, (Integer) param);

            } else {
                ps.setString(i++, (String) param);

            }
        }

    }
}

