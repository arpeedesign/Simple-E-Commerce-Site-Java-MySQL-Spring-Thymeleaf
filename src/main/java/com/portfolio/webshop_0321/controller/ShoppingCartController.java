package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.CartItem;
import com.portfolio.webshop_0321.entity.Customer;
import com.portfolio.webshop_0321.service.CustomerService;
import com.portfolio.webshop_0321.service.ShoppingCartService;
import com.portfolio.webshop_0321.service.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CustomerService customerService;

/*    @GetMapping("/cart")
    public String showShoppingCart(Model model, @AuthenticationPrincipal Authentication authentication){

        Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
        List<CartItem> cartItemList = shoppingCartService.listCartItems(customer);
        model.addAttribute("cartItems",cartItemList);
        model.addAttribute("pageTitle","Shopping Cart");
        return "shopping_cart";
    }*/

}
