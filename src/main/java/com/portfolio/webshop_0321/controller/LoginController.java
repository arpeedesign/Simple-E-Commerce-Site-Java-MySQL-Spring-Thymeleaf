package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.dto.UserDto;
import com.portfolio.webshop_0321.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView loginForm() {
        return new ModelAndView("login");// NEM USER KÉNE?
    }

    @GetMapping("/registration")
    public ModelAndView registrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return new ModelAndView("registration");
    }

    @PostMapping("/registration")
    public ModelAndView registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return new ModelAndView("registration");
        }

        userService.saveUserDto(userDto);
        return new ModelAndView("redirect:/registration?success");
    }

}
