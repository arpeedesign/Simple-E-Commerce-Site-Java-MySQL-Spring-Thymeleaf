package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.CartItem;
import com.portfolio.webshop_0321.entity.Customer;

import java.util.List;

public interface ShoppingCartService {
     List<CartItem> listCartItems(Customer customer);
}
