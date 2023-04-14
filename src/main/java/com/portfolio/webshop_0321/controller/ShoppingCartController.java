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
    public ModelAndView shoppingCart(Model model, @RequestParam(required = false) Long cartItemId, @RequestParam(required = false) Integer quantity) {
        ModelAndView mav = new ModelAndView("shopping-cart");
        List<CartItem> list = shoppingCartService.listCartItems(currentlyloggedinuser().getId()).stream().filter(c->!c.isOrdered()).collect(Collectors.toList());
        mav.addObject("cartitems", list);
        if (cartItemId!=null || quantity!=null){
            shoppingCartService.updateQuantity(cartItemId,quantity);
            mav.getModel().put("subtotal",(shoppingCartService.cartSubTotal(cartItemId)));
        }
        mav.addObject("totalcartprice",shoppingCartService.cartTotal(cartItemId));
        return  mav;
    }
    @GetMapping("/updateQuantity")
    public ModelAndView updateQuantity(@RequestParam(required = false) Long cartItemId, @RequestParam(required = false) int quantity) {
        ModelAndView mav = new ModelAndView("redirect:/shopping-cart");
        mav.getModel().put("subtotal",shoppingCartService.cartSubTotal(cartItemId));
        shoppingCartService.updateQuantity(cartItemId,quantity);
        return  mav;
    }

    @GetMapping("/addProductToCart")
    public ModelAndView addProductToCart(@RequestParam Long productId) {
        shoppingCartService.addProduct(productId);
        return  new ModelAndView("redirect:/shop");
    }
    @GetMapping("/removeProductFromCart")
    public ModelAndView removeProductFromCart(Long id) {
        shoppingCartService.removeProduct(id);
        ModelAndView mav = new ModelAndView("redirect:/shopping-cart");
        return  mav;
    }
    @GetMapping("/currentlyloggedinuser")
    public User currentlyloggedinuser() {
        return  userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    @GetMapping("/orderCartItems")
    public ModelAndView orderCartItems() {
        orderService.orderCartItems(shoppingCartService.getCurrentUser().getId());
        ModelAndView mav = new ModelAndView("redirect:/ordered");
        return  mav;
    }
    @GetMapping("/cancelOrderedCartItems")
    public ModelAndView cancelOrderedCartItems(@RequestParam(required = false) Long orderId) {
        //orderService.cancelOrderedCartItems(orderId);
        ModelAndView mav = new ModelAndView("redirect:/shopping-cart");
        return  mav;
    }
    @GetMapping("/ordered")
    public ModelAndView ordered() {
        return  new ModelAndView("ordered");
    }
}
