package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.dto.UserDto;
import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.service.ProductService;
import com.portfolio.webshop_0321.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        userService.createAdmin();
        userService.createFirstUser();
        productService.createFirstProducts();
        return new ModelAndView("home");
    }

    @GetMapping
    public ModelAndView currentUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model) {
        ModelAndView mav = new ModelAndView();
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        User user = userService.findUserByEmail(email);
        String userFirstName = user.getUserFirstName();
        mav.getModel().put("firstName", userFirstName);
        mav.getModel().put("emailAddress", email);
        mav.getModel().put("userRole",user.getRoles());
        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView mav = new ModelAndView("profile");
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        User user = userService.findUserByEmail(email);
        String userFirstName = user.getUserFirstName();
        mav.getModel().put("firstName", userFirstName);
        mav.getModel().put("emailAddress", email);
        mav.getModel().put("userRole",user.getRoles());
        return mav;
    }

}
