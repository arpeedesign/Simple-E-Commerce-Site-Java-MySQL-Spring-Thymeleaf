package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.service.ProductService;
import com.portfolio.webshop_0321.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @GetMapping({"/home", "", "/"})
    public ModelAndView home() {
      /*  userService.createAdmin();
        userService.createFirstUser();
        productService.createFirstProducts("Hp Mouse");*/
        return new ModelAndView("home");
    }
/*    @PostConstruct
    private void postConstruct() {
*//*        User admin = new User();
        admin.setUserEmail("admin@admin.com");
        admin.setUserFirstName("admin");
        admin.setUserLastName("admin");
        admin.setUserGender("X");
        admin.setUserPassword("Password");
        admin.setEnabled(true);
        //admin.setRoles();
        userService.saveUser(admin);*//*
        User user = new User();
        user.setUserEmail("user@user.com");
        user.setUserFirstName("user");
        user.setUserLastName("user");
        user.setUserGender("X");
        user.setUserPassword("Password");
        user.setEnabled(true);
        userService.saveUser(user);
    }*/
}
