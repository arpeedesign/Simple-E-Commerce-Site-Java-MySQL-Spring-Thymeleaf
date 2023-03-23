package com.portfolio.webshop_0321.repository;

import com.portfolio.webshop_0321.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUserEmailIgnoreCase(String emailId);
    Boolean existsByUserEmail(String email);
}
