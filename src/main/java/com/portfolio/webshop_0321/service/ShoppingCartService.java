package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.CartItem;
import com.portfolio.webshop_0321.entity.Customer;
import com.portfolio.webshop_0321.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ShoppingCartService {


    List<CartItem> listCartItems(Long id);

    User getCurrentlyLoggedInCustomer(Authentication authentication);

    Long addProduct(Long productId);

    void removeProduct(Long Id);

    String updateQuantity(String productId, int quantity, String customerId);
}
