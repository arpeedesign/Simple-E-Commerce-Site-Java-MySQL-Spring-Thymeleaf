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
    @Autowired
    ShoppingCartService shoppingCartService;

    @Override
    public void orderCartItems(Long userId) {
        List<CartItem> orderList = cartItemRepository.findByUserIdAtOrder(userId);
        Order order = new Order();
        order.setUser(userService.findID(userId));
        order.setOrderedCartTotalValue(shoppingCartService.cartTotal());
        orderRepository.save(order);
        for (CartItem i : orderList) {
            i.setOrdered(true);
            i.setOrder(order);
            cartItemRepository.save(i);
        }
    }

    @Override
    public void cancelOrderedCartItems(Long orderId) {
        List<CartItem> orderedCartItems = orderRepository.findById(orderId).get().getCartItem();
        for(CartItem i:orderedCartItems){
            i.setOrdered(false);
            cartItemRepository.save(i);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }
}
