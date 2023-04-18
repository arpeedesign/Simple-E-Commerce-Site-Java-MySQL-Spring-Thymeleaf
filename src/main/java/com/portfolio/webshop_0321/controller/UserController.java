package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/userlist")
    public ModelAndView showUsers() {
        ModelAndView mav = new ModelAndView("list-users");
        List<User> list = userService.findAll();
        mav.addObject("users", list);
        return mav;
    }
    @GetMapping("/addUserForm")
    public ModelAndView addUserForm() {
        ModelAndView mav = new ModelAndView("add-user-form");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }
    @PostMapping("/saveUser")
    public ModelAndView saveUser (User user) {
        userService.saveUser(user);
        return new ModelAndView("redirect:/userlist");
    }
    @GetMapping("/showUpdateUserForm")
    public ModelAndView showUpdateForm(@RequestParam Long userId) {
        ModelAndView mav = new ModelAndView("add-user-form");
        User user = userService.findID(userId);
        mav.addObject("user", user);
        return mav;
    }
    @GetMapping("/deleteUser")
    public ModelAndView deleteUser (@RequestParam Long userId) {
        userService.deleteUser(userId);
        return new ModelAndView("redirect:/userlist");
    }
    @GetMapping("/findUserById")
    public User findById(@RequestParam Long Id) {
        return userService.findID(Id);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }
    @GetMapping("/user/")
    public ModelAndView user() {
        return new ModelAndView("redirect:/user");
    }
}
