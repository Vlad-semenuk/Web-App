package com.epam.semeniuk.dto;

import com.epam.semeniuk.enums.SqlOrderByValue;

import java.util.List;

public class ProductFilterDTO {

    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer DEFAULT_PRODUCT_LIMIT = 4;

    private List<Integer> categories;
    private List<Integer> manufacturers;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer productLimit;
    private String name;
    private SqlOrderByValue orderBy;
    private Integer page;
    public ProductFilterDTO(){
        this.page = DEFAULT_PAGE;
        this.productLimit = DEFAULT_PRODUCT_LIMIT;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<Integer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Integer productLimit) {
        this.productLimit = productLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SqlOrderByValue getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(SqlOrderByValue orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ProductFilterDTO{" + '\'' +
                "categories=" + categories +  '\'' +
                ", manufacturers=" + manufacturers + '\'' +
                ", minPrice=" + minPrice + '\'' +
                ", maxPrice=" + maxPrice + '\'' +
                ", productLimit=" + productLimit + '\'' +
                ", name='" + name + '\'' +
                ", sortName='" + orderBy + '\'' +
                ", page = " + page +
                '}';
    }
}
