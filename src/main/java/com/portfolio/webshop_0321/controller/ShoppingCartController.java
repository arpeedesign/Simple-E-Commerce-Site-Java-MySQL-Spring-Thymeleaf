package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.CartItem;
import com.portfolio.webshop_0321.entity.Product;
import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.service.CustomerService;
import com.portfolio.webshop_0321.service.ProductService;
import com.portfolio.webshop_0321.service.ShoppingCartService;
import com.portfolio.webshop_0321.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    UserService userService;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return "authentication.getName() " + authentication.getName()
                + "\nauthentication.getDetails() " + authentication.getDetails()
                + "\nauthentication.getPrincipal() " +authentication.getPrincipal()
                +"\nUser has authorities: " + userDetails.getAuthorities()
                +"\nUsername: " + userDetails.getUsername();
    }
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
        List<CartItem> list = shoppingCartService.listCartItems(currentlyloggedinuser().getId());
        mav.addObject("cartitems", list);
        return  mav;
    }
    @GetMapping("/addProductToCart")
    public ModelAndView addProductToCart(@RequestParam Long productId) {
        //String email= SecurityContextHolder.getContext().getAuthentication().getName();
        shoppingCartService.addProduct(productId);
        return  new ModelAndView("redirect:/shop");
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
    @GetMapping("/currentlyloggedinuser")
    public User currentlyloggedinuser() {
        return  userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
