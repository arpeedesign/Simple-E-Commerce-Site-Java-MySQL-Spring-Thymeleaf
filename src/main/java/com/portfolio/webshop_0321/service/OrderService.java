package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.Order;

import java.util.List;

public interface OrderService {
    void orderCartItems(Long userId);
    void cancelOrderedCartItems(Long orderId);
    List<Order> findAllOrder();
}
