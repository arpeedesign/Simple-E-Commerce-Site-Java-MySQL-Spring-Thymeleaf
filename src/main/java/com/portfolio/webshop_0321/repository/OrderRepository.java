package com.portfolio.webshop_0321.repository;

import com.portfolio.webshop_0321.entity.Order;
import com.portfolio.webshop_0321.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
}
