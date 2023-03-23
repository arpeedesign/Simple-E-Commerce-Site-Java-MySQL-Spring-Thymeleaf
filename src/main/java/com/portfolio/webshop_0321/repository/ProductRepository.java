package com.portfolio.webshop_0321.repository;

import com.portfolio.webshop_0321.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository  extends CrudRepository<Product, Long> {
    List<Product> findAll();
}
