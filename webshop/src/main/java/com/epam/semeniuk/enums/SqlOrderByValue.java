package com.epam.semeniuk.enums;

import java.util.Arrays;

public enum SqlOrderByValue {
    NAME_ASC("product_name ASC", 1), NAME_DESC("product_name DESC", 2), PRICE_ASC("product_price ASC", 3), PRICE_DESC("product_price DESC", 4);

    private final String column;
    private final int id;

    SqlOrderByValue(String column, int id){
        this.column = column;
        this.id = id;
    }

    public static SqlOrderByValue getOrderByValue(int id) {
        return Arrays.stream(SqlOrderByValue.values()).filter(i -> i.id == id).findFirst().orElse(null);
    }

    public String getColumn(){
        return this.column;
    }
}
