package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface UserService {
    void saveUserDto(UserDto userDto);
    User findUserByEmail(String email);

    List<User> findAll();
    ResponseEntity<?> saveUser(User user);
    User findID(Long userId);
    void deleteUser(Long userId);
    ModelAndView confirmEmail(String confirmationToken);
    void createAdmin();
    void createFirstUser();

}
