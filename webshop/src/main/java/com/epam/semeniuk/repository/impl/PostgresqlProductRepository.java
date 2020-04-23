package com.epam.semeniuk.repository.impl;

import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.entity.Product;
import com.epam.semeniuk.mapper.Mapper;
import com.epam.semeniuk.mapper.ProductMapper;
import com.epam.semeniuk.querybuilder.GetFilteredProductSqlQuery;
import com.epam.semeniuk.repository.ItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PostgresqlProductRepository implements ItemRepository<Product> {

    private Mapper<Product> mapper;

    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products p  INNER JOIN categories c on p.product_category = c.category_id INNER JOIN manufactorers m on p.product_manufacturer = m.manufacturer_id WHERE product_id = ?";
    private static final String IS_EXISTS_ID = "SELECT EXISTS (SELECT product_id FROM products WHERE product_id = ?);";

    public PostgresqlProductRepository() {
        this.mapper = new ProductMapper();
    }


    @Override
    public List<Product> getAll(Connection connection, ProductFilterDTO filterDTO) throws SQLException {
        GetFilteredProductSqlQuery filteredProductSqlQuery = new GetFilteredProductSqlQuery();
        String sqlQuery = filteredProductSqlQuery.buildAllProductQuery(filterDTO);
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            filteredProductSqlQuery.setPreparedStatementParameters(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(mapper.extract(resultSet));
            }
        }
        return products;
    }

    @Override
    public int getCount(Connection connection, ProductFilterDTO filterDTO) throws SQLException {
        int result = 0;
        GetFilteredProductSqlQuery filteredProductSqlQuery = new GetFilteredProductSqlQuery();
        String sqlQuery = filteredProductSqlQuery.buildCountProductsQuery(filterDTO);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            filteredProductSqlQuery.setPreparedStatementParameters(statement);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("count");
            }
        }
        return result;
    }

    @Override
    public Product getItemByID(Connection connection, int id) throws SQLException {
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = mapper.extract(resultSet);
            }
        }
        return product;
    }

    @Override
    public boolean isItemExist(Connection connection, int id) throws SQLException {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(IS_EXISTS_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBoolean("exists");
            }
        }
        return result;
    }
}