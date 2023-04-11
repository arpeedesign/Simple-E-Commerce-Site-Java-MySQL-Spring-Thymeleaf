package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.*;
import com.portfolio.webshop_0321.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @Override
    public List<CartItem> listCartItems(Long id) {
        return cartItemRepository.findByUser(id);
    }

    @Override
    public User getCurrentlyLoggedInCustomer(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findUserByEmail(userDetails.getUsername());
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        return userService.findUserByEmail(email);
    }

    @Override
    public Long addProduct(Long productId) {
        List<CartItem> list = listCartItems(getCurrentUser().getId());
        for (CartItem i:list) {
            if(i.getProduct().getProductId()==productId){
                i.setQuantity(i.getQuantity()+1);
                cartItemRepository.save(i);
                return i.getId();
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setUser(getCurrentUser());
        cartItem.setProduct(productService.findID(productId));
        cartItem.setQuantity(1);
        cartItemRepository.save(cartItem);
        return cartItem.getId();
    }

    @Override
    public void removeProduct(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void updateQuantity(Long cartItemId, int quantity) {
        CartItem currentCartItem = cartItemRepository.findByCartItemId(cartItemId);
        currentCartItem.setQuantity(quantity);
        cartItemRepository.save(currentCartItem);
    }

    @Override
    public Double cartSubTotal(Long cartItemId) {
        Double subtotal=null;
        subtotal =cartItemRepository.findByCartItemId(cartItemId).getQuantity()*cartItemRepository.findByCartItemId(cartItemId).getProduct().getProductPrice();
        return  subtotal;
    }

    @Override
    public Double cartTotal(Long cartItemId) {
        List<CartItem> list = listCartItems(getCurrentUser().getId());
        Double cartTotal=null;
        for (CartItem i:list) {
            cartTotal = cartTotal + i.getQuantity() * i.getProduct().getProductPrice();
        }return  cartTotal;
    }

}
