package com.epam.semeniuk.service.dbService;

import com.epam.semeniuk.dao.transaction.TransactionManager;
import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.entity.Product;
import com.epam.semeniuk.repository.ItemRepository;
import com.epam.semeniuk.service.ProductService;

import java.util.List;

public class DBProductService implements ProductService {

    private TransactionManager transactionManager;
    private ItemRepository repository;


    public DBProductService(TransactionManager transactionManager, ItemRepository repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;

    }


    @Override
    public List<Product> getAllProductsFromSqlQuery(ProductFilterDTO productFilterDTO) {

        return transactionManager.execute(connection -> repository.getAll(connection, productFilterDTO));
    }


    @Override
    public int getCountProductsFromSqlQuery(ProductFilterDTO productFilterDTO) {
        return transactionManager.execute(connection -> repository.getCount(connection, productFilterDTO));
    }

    @Override
    public Product getProductById(int productId) {
        return (Product) transactionManager.execute(connection -> repository.getItemByID(connection, productId));
    }

    @Override
    public boolean isProductExist(int id) {
        return transactionManager.execute(connection -> repository.isItemExist(connection, id));
    }
}
