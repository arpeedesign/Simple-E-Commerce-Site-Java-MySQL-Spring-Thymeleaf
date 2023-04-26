package com.portfolio.webshop_0321.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="dashboard_data")
@Entity
public class DashboardData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dashboard_Id")
    private long dashboardId;
    @Column(name = "sales_date")
    private LocalDate salesDate;
    @Column(name = "sold_product_name")
    private String soldProductName;
    @Column(name = "sold_product_type")
    private String soldProductType;
    @Column(name = "sold_product_price")
    private Double soldProductPrice;
    @Column(name = "sold_product_cog")
    private Double soldProductCog;
    @Column(name = "sold_product_vat")
    private Double soldProductVat;
    @Column(name = "sold_product_pieces")
    private Double soldProductPieces;
    @Column(name = "sold_product_country")
    private String soldProductCountry;


}
