package com.epam.semeniuk.service.dbService;

import com.epam.semeniuk.dao.transaction.TransactionManager;
import com.epam.semeniuk.entity.Category;
import com.epam.semeniuk.repository.CategoryRepository;
import com.epam.semeniuk.service.CategoryService;

import java.util.List;

public class DBCategoryService implements CategoryService {


    private TransactionManager transactionManager;
    private CategoryRepository repository;

    public DBCategoryService(TransactionManager transactionManager, CategoryRepository repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;
    }

    @Override
    public List<Category> getAllCategories() {
        return transactionManager.execute(connection -> repository.getAllCategories(connection));
    }
}
