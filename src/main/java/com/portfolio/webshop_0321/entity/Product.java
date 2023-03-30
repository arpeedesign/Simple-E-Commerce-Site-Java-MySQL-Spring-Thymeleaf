package com.portfolio.webshop_0321.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")//SQL table name
@Entity
public class Product {
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_type")
    private String productType;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private long productId;
    @Column(name = "product_pic")
    private String productPic;
    @Column(name = "product_price", nullable=false)
    private double productPrice;

    public String getProductName() {
        return productName;
    }
}
