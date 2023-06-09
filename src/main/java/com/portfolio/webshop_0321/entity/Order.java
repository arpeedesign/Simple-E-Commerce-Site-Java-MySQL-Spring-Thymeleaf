package com.portfolio.webshop_0321.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
    @OneToMany(mappedBy = "order")
    private List<CartItem> cartItem;

    private double orderedCartTotalValue;
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<CartItem> getCartItem() {
        return cartItem;
    }
    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }
    public double getOrderedCartTotalValue() {
        return orderedCartTotalValue;
    }
    public void setOrderedCartTotalValue(double orderedCartTotalValue) {
        this.orderedCartTotalValue = orderedCartTotalValue;
    }


}
