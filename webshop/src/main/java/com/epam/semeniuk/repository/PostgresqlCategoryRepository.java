package com.epam.semeniuk.repository;

import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.entity.Category;
import com.epam.semeniuk.entity.Product;
import com.epam.semeniuk.mapper.CategoryMapper;
import com.epam.semeniuk.mapper.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlCategoryRepository implements Repository<Category> {

    private static final String GET_ALL_CATEGORY = "SELECT * FROM categories;";

    private Mapper<Category> mapper;

    public PostgresqlCategoryRepository (){
        this.mapper = new CategoryMapper();
    }

    @Override
    public List<Category> getAll(Connection connection, ProductFilterDTO filterDTO) throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CATEGORY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(mapper.extract(resultSet));
            }
        }
        return categories;
    }

    @Override
    public int getCount(Connection connection, ProductFilterDTO filterDTO) throws SQLException {
        return 0;
    }

    @Override
    public Category getItemByID(Connection connection, int id) throws SQLException {
        return null;
    }
}
