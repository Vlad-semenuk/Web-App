package com.epam.semeniuk.service;

import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
}
