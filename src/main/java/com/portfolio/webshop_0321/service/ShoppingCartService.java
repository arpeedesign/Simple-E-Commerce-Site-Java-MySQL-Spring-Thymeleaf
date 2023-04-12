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
    User getCurrentUser();

    void removeProduct(Long Id);

    void updateQuantity(Long cartItemId,int quantity);
    Double cartSubTotal(Long cartItemId);
    Double cartTotal(Long cartitemId);

}
