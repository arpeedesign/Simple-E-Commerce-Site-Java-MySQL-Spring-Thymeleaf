package com.portfolio.webshop_0321.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class Controller {

   @GetMapping({"/home", "", "/"})
   public ModelAndView home() {
       return new ModelAndView("home");
    }
}
