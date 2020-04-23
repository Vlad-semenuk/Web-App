package com.epam.semeniuk.mapper;

import com.epam.semeniuk.entity.Category;
import com.epam.semeniuk.entity.Manufacturer;
import com.epam.semeniuk.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements Mapper<Product> {

    private static final String PRODUCT_ID = "product_id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_DESCRIPTION = "product_description";
    private static final String PRODUCT_PRICE = "product_price";
    private static final String PRODUCT_IMAGE = "product_image";
    private Mapper<Category> categoryMapper;
    private Mapper<Manufacturer> manufacturerMapper;

    public ProductMapper() {
        categoryMapper = new CategoryMapper();
        manufacturerMapper = new ManufacturerMapper();
    }

    @Override
    public Product extract(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(PRODUCT_ID));
        product.setName(resultSet.getString(PRODUCT_NAME));
        product.setDescription(resultSet.getString(PRODUCT_DESCRIPTION));
        product.setPrice(resultSet.getBigDecimal(PRODUCT_PRICE));
        product.setImage(resultSet.getString(PRODUCT_IMAGE));
        Category category = categoryMapper.extract(resultSet);
        product.setCategory(category);
        Manufacturer manufacturer = manufacturerMapper.extract(resultSet);
        product.setManufacturer(manufacturer);
        return product;
    }
}
