package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.Product;
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
    public void createFirstProducts() {
        if (Boolean.FALSE.equals(productRepository.existsByProductName("Hp Mouse"))) {
            Product product = new Product();
            product.setProductName("Hp Mouse");
            product.setProductType("Electronics");
            product.setProductPic("/static/img/product-01.jpg");
            product.setProductPrice(19.9);
            productRepository.save(product);
        }
        if (Boolean.FALSE.equals(productRepository.existsByProductName("IPhone 14 Pro"))) {
            Product product = new Product();
            product.setProductName("IPhone 14 Pro");
            product.setProductType("Phone");
            product.setProductPic("/static/img/product-01.jpg");
            product.setProductPrice(1119.9);
            productRepository.save(product);
        }
        if (Boolean.FALSE.equals(productRepository.existsByProductName("EVOLVEO Ptero ZX"))) {
            Product product = new Product();
            product.setProductName("EVOLVEO Ptero ZX");
            product.setProductType("Chair");
            product.setProductPic("/static/img/product-01.jpg");
            product.setProductPrice(149.9);
            productRepository.save(product);
        }
    }


}
