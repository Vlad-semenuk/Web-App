package com.epam.semeniuk.repository.impl;

import com.epam.semeniuk.entity.Category;
import com.epam.semeniuk.mapper.CategoryMapper;
import com.epam.semeniuk.mapper.Mapper;
import com.epam.semeniuk.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlCategoryRepository implements CategoryRepository {

    private static final String GET_ALL_CATEGORY = "SELECT * FROM categories;";

    private Mapper<Category> mapper;

    public PostgresqlCategoryRepository() {
        this.mapper = new CategoryMapper();
    }

    @Override
    public List<Category> getAllCategories(Connection connection) throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CATEGORY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(mapper.extract(resultSet));
            }
        }
        return categories;
    }
}
