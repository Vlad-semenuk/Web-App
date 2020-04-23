package com.epam.semeniuk.service;

import com.epam.semeniuk.dto.ProductFilterDTO;
import com.epam.semeniuk.entity.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> getAllManufacturer();
}
