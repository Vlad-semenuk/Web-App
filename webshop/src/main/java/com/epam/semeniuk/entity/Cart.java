package com.epam.semeniuk.entity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> cart;

    private static final String CART = "cart";

    public Cart() {
        cart = new HashMap<>();
    }

    public void addToCart(Product product, int quantity) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
        } else {
            cart.put(product, quantity);
        }
    }

    public void editProductQuantity(Product product, int quantity){
        if (cart.containsKey(product)){
            cart.put(product, quantity);
        }
    }

    public void deleteFromCart(Product product) {
        cart.remove(product);
    }

    public void clearCart() {
        cart.clear();
    }

    public Map<Product, Integer> getAllItems() {
        return cart;
    }

    public int getQuantityProduct() {
        return cart.values()
                .stream()
                .mapToInt(value -> value)
                .sum();
    }

    public int getTotalPrice() {
        return cart.entrySet()
                .stream()
                .mapToInt(value -> value.getKey().getPrice().intValue() * value.getValue())
                .sum();
    }

    public static Cart getCartFromRequest(HttpServletRequest req) {
        if (req.getSession().getAttribute(CART) == null) {
            return new Cart();
        }
        return (Cart) req.getSession().getAttribute(CART);
    }

    @Override
    public String toString() {
        return cart.toString();
    }
}
