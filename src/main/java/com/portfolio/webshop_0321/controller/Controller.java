package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.service.ProductService;
import com.portfolio.webshop_0321.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@RestController
public class Controller {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @GetMapping({"/home", "", "/"})
    public ModelAndView home() {
        userService.createAdmin();
        userService.createFirstUser();
        productService.createFirstProducts();
        return new ModelAndView("home");
    }
    @ModelAttribute("loggedinuser")
    public ModelAndView globalUserObject() {
        ModelAndView mav = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            mav.getModel().put("userFirstName", "Nobody");
            return mav;
        }
        User user = userService.findUserByEmail(authentication.getName());
        if (user == null) {
            mav.getModel().put("userFirstName", "Unknown");
            return mav;
        }
        mav.getModel().put("userFirstName", user.getUserFirstName());
        System.out.println("globalUserObject called");
        return mav;
    }


    @PostConstruct
    private void postConstruct() {
        //userService.createAdmin();
        //userService.createFirstUser();
    }

}
