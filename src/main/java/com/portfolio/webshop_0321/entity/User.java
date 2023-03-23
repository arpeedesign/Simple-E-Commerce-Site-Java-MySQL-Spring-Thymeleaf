package com.portfolio.webshop_0321.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")//SQL table name
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private long userId;
    @Column(name = "first_name", nullable = false, length = 40)
    private String userFirstName;
    @Column(name = "last_name", nullable = false, length = 40)
    private String userLastName;
    @Column(name = "email",nullable = false, unique = true, length = 60)
    private String userEmail;
    @Column(name = "password",nullable = false, length = 64)
    private String userPassword;
    @Column(name = "gender",nullable = false, length = 1)
    private String userGender;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

}
