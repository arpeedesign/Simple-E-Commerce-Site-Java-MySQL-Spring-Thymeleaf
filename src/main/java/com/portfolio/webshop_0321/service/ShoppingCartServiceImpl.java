package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.*;
import com.portfolio.webshop_0321.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductService productService;

    @Override
    public List<CartItem> listCartItems(Customer customer) {
        return cartItemRepository.findByCustomer(customer);
    }
/*public User getCurrentlyLoggedInCustomer(Authentication authentication) {
    User user=null;
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails){
        user = ((UserDetails) principal).getUsername();
    } else if ( principal instanceof CustomOAuth2User) {
        String email= ((CustomOAuth2User)principal).getEmail();
        user = getUserByEmail(email);
    }
    return user;
}*/
    @Override
    public void addProduct(Long productId) {
        productService.findID(productId);
    //CartItem cartItem = new CartItem(id,);


      //  cartItemRepository.save(cartItem);
    }
    @Override
    public void removeProduct(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public String updateQuantity(String product_id, int quantity, String customer_id) {
        return null;
    }

}
