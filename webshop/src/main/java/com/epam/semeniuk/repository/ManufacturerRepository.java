package com.epam.semeniuk.repository;

import com.epam.semeniuk.entity.Manufacturer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ManufacturerRepository {

    List<Manufacturer> getAllManufacturers(Connection connection) throws SQLException;
}
