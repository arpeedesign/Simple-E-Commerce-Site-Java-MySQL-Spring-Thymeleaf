package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.Product;
import com.portfolio.webshop_0321.service.CustomerService;
import com.portfolio.webshop_0321.service.ProductService;
import com.portfolio.webshop_0321.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
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
    @GetMapping("/shopping-cart")
    public ModelAndView shoppingCart(Model model) {
        ModelAndView mav = new ModelAndView("shopping-cart");
        return  mav;
    }
    @GetMapping("/addProductToCart")
    public ModelAndView addProductToCart(Long productId) {
        ModelAndView mav = new ModelAndView("addProductToCart");
        shoppingCartService.addProduct(productId);
        return  mav;
    }

    @GetMapping("/removeProductFromCart")
    public ModelAndView removeProductFromCart(Long id) {
        ModelAndView mav = new ModelAndView("removeProductFromCart");
        shoppingCartService.removeProduct(id);
        return  mav;
    }
    @GetMapping("/updateQuantity")
    public ModelAndView updateQuantity() {
        ModelAndView mav = new ModelAndView("updateQuantity");
        return  mav;
    }

}
