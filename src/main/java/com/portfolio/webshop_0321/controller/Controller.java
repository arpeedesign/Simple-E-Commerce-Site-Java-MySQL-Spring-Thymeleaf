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
        return new ModelAndView("home");
    }

    @PostConstruct
    private void postConstruct() {
        userService.createAdmin();
        //userService.createFirstUser();
    }

}
