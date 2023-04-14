package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.Product;
import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
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

    @Override
    public void createFirstProducts(String name) {
        if(productRepository.findByProductName("Hp Mouse").getProductName()!="Hp Mouse") {
            Product product1 = new Product();
            product1.setProductName("Hp Mouse");
            product1.setProductType("Electronics");
            product1.setProductPic("/static/img/product-01.jpg");
            product1.setProductPrice(19.9);
            productRepository.save(product1);
        }
    }


}
