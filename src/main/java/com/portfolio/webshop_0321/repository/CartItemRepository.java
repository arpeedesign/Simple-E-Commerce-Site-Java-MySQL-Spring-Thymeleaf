package com.portfolio.webshop_0321.repository;

import com.portfolio.webshop_0321.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

 @Query(value = "SELECT * FROM cart_items Where user_id=?1 ", nativeQuery = true)
 List<CartItem> findByUser (Long id);
 @Query(value = "SELECT * FROM cart_items Where (user_id=?1 AND ordered = false)", nativeQuery = true)
 List<CartItem> findByUserIdAtOrder (Long id);
 @Query(value = "SELECT * FROM cart_items Where id=?1 ", nativeQuery = true)
 CartItem findByCartItemId (Long cartItemId);

}
