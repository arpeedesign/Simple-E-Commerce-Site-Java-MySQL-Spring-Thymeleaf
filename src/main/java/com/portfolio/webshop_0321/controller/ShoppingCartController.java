package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.CartItem;
import com.portfolio.webshop_0321.entity.Product;
import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    OrderService orderService;

    @GetMapping("/shop")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("shop");
        List<Product> list = productService.findAll();
        mav.addObject("products", list);
        if (currentlyloggedinuser() == null) {
            return mav;
        }
        return mav;
    }

    @GetMapping("/shopping-cart")
    public ModelAndView shoppingCart(Model model, @RequestParam(required = false) Long cartItemId, @RequestParam(required = false) Integer quantity) {
        ModelAndView mav = new ModelAndView("shopping-cart");
        if (currentlyloggedinuser() == null) {
            mav.getModel().put("totalcartprice", 0.0);
            return mav;
        }
        if (cartItemId != null && quantity == null) {
            shoppingCartService.removeProduct(cartItemId);
            mav.getModel().put("subtotal", (shoppingCartService.cartSubTotal(cartItemId)));
        }
        if (cartItemId != null || quantity != null) {
            shoppingCartService.updateQuantity(cartItemId, quantity);
            mav.getModel().put("subtotal", (shoppingCartService.cartSubTotal(cartItemId)));
        }
        List<CartItem> list = shoppingCartService.listCartItems(currentlyloggedinuser().getId()).stream().filter(c -> !c.isOrdered()).collect(Collectors.toList());
        mav.addObject("cartitems", list);
        mav.addObject("totalcartprice", shoppingCartService.cartTotal());
        return mav;
    }

    @GetMapping("/updateQuantity")
    public ModelAndView updateQuantity(@RequestParam(required = false) Long cartItemId, @RequestParam(required = false) int quantity) {
        ModelAndView mav = new ModelAndView("redirect:/shopping-cart");
        shoppingCartService.updateQuantity(cartItemId, quantity);
        mav.getModel().put("subtotal", shoppingCartService.cartSubTotal(cartItemId));
        return mav;
    }


    @GetMapping("/addProductToCart")
    public ModelAndView addProductToCart(@RequestParam Long productId) {
        ModelAndView mav = new ModelAndView("redirect:/shop");
        if (currentlyloggedinuser() == null) {
            return mav;
        }
        shoppingCartService.addProduct(productId);
        return mav;
    }

    @GetMapping("/removeProductFromCart")
    public ModelAndView removeProductFromCart(Long id) {
        shoppingCartService.removeProduct(id);
        ModelAndView mav = new ModelAndView("redirect:/shopping-cart");
        return mav;
    }

    @GetMapping("/orderCartItems")
    public ModelAndView orderCartItems() {
        if (currentlyloggedinuser() == null) {
            return new ModelAndView("redirect:/shopping-cart");
        }
        orderService.orderCartItems(shoppingCartService.getCurrentUser().getId());
        return new ModelAndView("redirect:/ordered");
    }

    @GetMapping("/ordered")
    public ModelAndView ordered() {
        return new ModelAndView("ordered");
    }

    @GetMapping("/currentlyloggedinuser")
    public User currentlyloggedinuser() {
        return userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return "authentication.getName() " + authentication.getName()
                + "\nauthentication.getDetails() " + authentication.getDetails()
                + "\nauthentication.getPrincipal() " + authentication.getPrincipal()
                + "\nUser has authorities: " + userDetails.getAuthorities()
                + "\nUsername: " + userDetails.getUsername();
    }

    @GetMapping("/cancelOrderedCartItems")
    public ModelAndView cancelOrderedCartItems(@RequestParam(required = false) Long orderId) {
        //orderService.cancelOrderedCartItems(orderId);
        ModelAndView mav = new ModelAndView("redirect:/shopping-cart");
        return mav;
    }
}
