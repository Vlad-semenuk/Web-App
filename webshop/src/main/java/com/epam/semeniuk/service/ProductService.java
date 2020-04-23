package com.epam.semeniuk.service;

import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProductsFromSqlQuery(ProductFilterDTO productFilterDTO);

    int getCountProductsFromSqlQuery(ProductFilterDTO productFilterDTO);

    Product getProductById(int productId);

    boolean isProductExist(int id);
}
