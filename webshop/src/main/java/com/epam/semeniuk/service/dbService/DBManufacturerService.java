package com.epam.semeniuk.service.dbService;

import com.epam.semeniuk.dao.transaction.TransactionManager;
import com.epam.semeniuk.entity.Manufacturer;
import com.epam.semeniuk.repository.CategoryRepository;
import com.epam.semeniuk.repository.ManufacturerRepository;
import com.epam.semeniuk.service.ManufacturerService;

import java.util.List;

public class DBManufacturerService implements ManufacturerService {

    private TransactionManager transactionManager;
    private ManufacturerRepository repository;

    public DBManufacturerService(TransactionManager transactionManager, ManufacturerRepository repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return transactionManager.execute(connection -> repository.getAllManufacturers(connection));
    }
}
