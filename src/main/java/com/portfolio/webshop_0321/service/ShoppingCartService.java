package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.CartItem;
import com.portfolio.webshop_0321.entity.Customer;

import java.util.List;

public interface ShoppingCartService {
    List<CartItem> listCartItems(Customer customer);

    void addProduct(Long productId);

    void removeProduct(Long Id);

    String updateQuantity(String productId, int quantity, String customerId);
}
