package com.epam.semeniuk.mapper;

import com.epam.semeniuk.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements Mapper<Category> {

    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME = "category_name";

    @Override
    public Category extract(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(CATEGORY_ID));
        category.setName(resultSet.getString(CATEGORY_NAME));
        return category;
    }
}
