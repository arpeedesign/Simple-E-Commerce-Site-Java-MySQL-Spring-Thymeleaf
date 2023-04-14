package com.portfolio.webshop_0321.service;

public interface OrderService {
    void orderCartItems(Long userId);
    void cancelOrderedCartItems(Long orderId);
}
