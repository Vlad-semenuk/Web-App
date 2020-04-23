package com.epam.semeniuk.mapper;

import com.epam.semeniuk.entity.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerMapper implements Mapper<Manufacturer> {

    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String MANUFACTURER_NAME = "manufacturer_name";

    @Override
    public Manufacturer extract(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getInt(MANUFACTURER_ID));
        manufacturer.setName(resultSet.getString(MANUFACTURER_NAME));
        return manufacturer;
    }
}
