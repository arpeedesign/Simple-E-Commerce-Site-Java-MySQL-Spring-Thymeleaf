package com.portfolio.webshop_0321.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="dashboard")//SQL table name
@Entity
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dashboard_Id")
    private long dashboardId;
    @Column(name = "sales_date")
    private String salesDate;
    @Column(name = "sold_product_name")
    private String soldProductName;
    @Column(name = "sold_product_type")
    private String soldProductType;
    @Column(name = "sold_product_price")
    private int soldProductPrice;
    @Column(name = "sold_product_cog")
    private int soldProductCog;
    @Column(name = "sold_product_vat")
    private int soldProductVat;
    @Column(name = "sold_product_pieces")
    private int soldProductPieces;
    @Column(name = "sold_product_country")
    private String soldProductCountry;


}
