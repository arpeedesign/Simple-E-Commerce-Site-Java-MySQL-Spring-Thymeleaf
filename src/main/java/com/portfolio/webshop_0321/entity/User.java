package com.portfolio.webshop_0321.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")//SQL table name
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private long id;
    @Column(name = "first_name", nullable = true, length = 40)
    private String userFirstName;
    @Column(name = "last_name", nullable = true, length = 40)
    private String userLastName;
    @Column(name = "email",nullable = true, unique = true, length = 60)
    private String userEmail;
    @Column(name = "password",nullable = true, length = 64)
    private String userPassword;
    @Column(name = "gender", length = 1)
    private String userGender;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_Id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();
    public User(String firstName,String lastName, String email, String password, String gender, List<Role> roles) {
        this.userFirstName = firstName;
        this.userLastName = lastName;
        this.userEmail = email;
        this.userPassword = password;
        this.userGender = gender;
        this.roles = roles;
    }

}
