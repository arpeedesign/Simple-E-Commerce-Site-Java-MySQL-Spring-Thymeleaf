package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.Product;
import com.portfolio.webshop_0321.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceIml implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
    @Override
    public Product findID(Long productID) {
        return productRepository.findById(productID).get();
    }
    @Override
    public void deleteProduct(Long productID) {
        productRepository.deleteById(productID);
    }

}
