package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.Product;
import com.portfolio.webshop_0321.service.CustomerService;
import com.portfolio.webshop_0321.service.ProductService;
import com.portfolio.webshop_0321.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @GetMapping("/shop")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("shop");
        List<Product> list = productService.findAll();
        mav.addObject("products", list);
        return  mav;
    }
}
