package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> findAll();
    ResponseEntity<?> saveUser(User user);
    User findID(Long userId);
    void deleteUser(Long userId);
    ResponseEntity<?> confirmEmail(String confirmationToken);
}