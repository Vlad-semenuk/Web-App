package com.epam.semeniuk.repository;

import com.epam.semeniuk.dto.ProductFilterDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemRepository<T> {

    List<T> getAll(Connection connection, ProductFilterDTO filterDTO) throws SQLException;

    int getCount(Connection connection, ProductFilterDTO filterDTO) throws SQLException;

    T getItemByID(Connection connection, int id) throws SQLException;

    boolean isItemExist(Connection connection, int id) throws SQLException;
}
