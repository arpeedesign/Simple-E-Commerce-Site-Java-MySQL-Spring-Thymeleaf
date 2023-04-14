package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void saveProduct(Product product);

    Product findID(Long productId);

    void deleteProduct(Long productId);
    void createFirstProducts(String name);
}
