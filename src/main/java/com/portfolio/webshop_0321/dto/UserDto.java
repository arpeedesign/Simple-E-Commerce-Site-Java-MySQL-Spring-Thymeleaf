package com.portfolio.webshop_0321.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "Please enter valid first name.")
    private String firstName;
    @NotEmpty(message = "Please enter valid last name.")
    private String lastName;
    @NotEmpty(message = "Please enter valid email.")
    @Email
    private String email;
    @NotEmpty(message = "Please enter valid password.")
    private String password;
    @NotEmpty(message = "Please choose valid option.")
    private String gender;
}
