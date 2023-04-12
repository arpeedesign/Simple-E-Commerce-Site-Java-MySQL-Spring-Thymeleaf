package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.CartItem;
import com.portfolio.webshop_0321.entity.Order;
import com.portfolio.webshop_0321.repository.CartItemRepository;
import com.portfolio.webshop_0321.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void orderCartItems(Long userId) {
        List<CartItem> orderList = cartItemRepository.findByUserIdAtOrder(userId);
        Order order = new Order();
        order.setUser(userService.findID(userId));
        order.setCartItem(orderList);
        for (CartItem i : orderList) {
            i.setOrdered(true);
            i.setOrder(order);
        }
        orderRepository.save(order);
    }
}
