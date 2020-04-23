package com.epam.semeniuk.repository;


import com.epam.semeniuk.entity.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CategoryRepository {

    List<Category> getAllCategories(Connection connection) throws SQLException;
}
