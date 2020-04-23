package com.epam.semeniuk.repository;

import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.entity.Manufacturer;
import com.epam.semeniuk.mapper.ManufacturerMapper;
import com.epam.semeniuk.mapper.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlManufacturerRepository implements Repository<Manufacturer> {

    private static final String GET_ALL_MANUFACTURERS = "SELECT * FROM manufactorers;";
    private Mapper<Manufacturer> mapper;

    public PostgresqlManufacturerRepository() {
        this.mapper = new ManufacturerMapper();
    }

    @Override
    public List<Manufacturer> getAll(Connection connection, ProductFilterDTO filterDTO) throws SQLException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_MANUFACTURERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                manufacturers.add(mapper.extract(resultSet));
            }
        }
        return manufacturers;
    }

    @Override
    public int getCount(Connection connection, ProductFilterDTO filterDTO) throws SQLException {
        return 0;
    }

    @Override
    public Manufacturer getItemByID(Connection connection, int id) throws SQLException {
        return null;
    }
}
