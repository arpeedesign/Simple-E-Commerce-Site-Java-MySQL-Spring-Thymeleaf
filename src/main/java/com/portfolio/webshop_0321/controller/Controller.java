package com.portfolio.webshop_0321.controller;

import org.springframework.core.Ordered;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class Controller {

   @GetMapping({"/","/home", ""})
   public ModelAndView home() {
       ModelAndView mav = new ModelAndView("home");
       return mav;
    }

    @GetMapping("/hello")
    public ModelAndView hello(Model model) {
        model.addAttribute("message","Ez egy teszt szöveg");
        ModelAndView mav = new ModelAndView("hello");
        return mav;
    }

}
