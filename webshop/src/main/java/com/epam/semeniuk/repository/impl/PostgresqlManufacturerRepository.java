package com.epam.semeniuk.repository.impl;

import com.epam.semeniuk.entity.Manufacturer;
import com.epam.semeniuk.mapper.ManufacturerMapper;
import com.epam.semeniuk.mapper.Mapper;
import com.epam.semeniuk.repository.CategoryRepository;
import com.epam.semeniuk.repository.ManufacturerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlManufacturerRepository implements ManufacturerRepository {

    private static final String GET_ALL_MANUFACTURERS = "SELECT * FROM manufactorers;";
    private Mapper<Manufacturer> mapper;

    public PostgresqlManufacturerRepository() {
        this.mapper = new ManufacturerMapper();
    }

    @Override
    public List<Manufacturer> getAllManufacturers(Connection connection) throws SQLException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_MANUFACTURERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                manufacturers.add(mapper.extract(resultSet));
            }
        }
        return manufacturers;
    }
}
