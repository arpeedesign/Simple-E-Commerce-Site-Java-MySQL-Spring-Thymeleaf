package com.portfolio.webshop_0321.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")//SQL table name
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;
    @Column(name = "email", nullable = false, unique = true, length = 60)
    private String customerEmail;
    @Column(name = "password", nullable = false, unique = true, length = 64)
    private String customerPassword;
    @Column(name = "first_name", nullable = false, length = 40)
    private String customerFirstName;
    @Column(name = "last_name", nullable = false, length = 40)
    private String customerLastName;
    @Column(name = "phone_number", nullable = false, length = 40)
    private String customerPhoneNumber;
    @Column(name = "address_line1", nullable = false, length = 64)
    private String customerAddressLine1;
    @Column(name = "address_line2", nullable = false, length = 64)
    private String customerAddressLine2;
    @Column(name = "city", nullable = false, length = 40)
    private String customerCity;
    @Column(name = "state", nullable = false, length = 40)
    private String customerState;
    @Column(name = "country_id", nullable = false, length = 40)
    private int customerCountryID;
    @Column(name = "postal_code", nullable = false, length = 40)
    private String customerPostalCode;
    //created_time
    @Column(name = "is_enabled")
    private Boolean costumerIsEnabled;
    @Column(name = "verification_code", nullable = false, length = 64)
    private String costumerVerificationCode;
    @Column(name = "auth_provider", nullable = false, length = 40)
    private String costumerAuthProvider;
    @Column(name = "reset_password_token", nullable = false, length = 40)
    private String costumerResetPasswordToken;

}
