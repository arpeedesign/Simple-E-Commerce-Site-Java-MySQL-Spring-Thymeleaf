package com.portfolio.webshop_0321.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests

                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/dashboard**").hasAnyRole("ADMIN")
                        .requestMatchers("/list**").hasAnyRole("ADMIN")
                        .requestMatchers("/addProductForm**").hasAnyRole("ADMIN")
                        .requestMatchers("/addBulkProductForm**").hasAnyRole("ADMIN")
                        .requestMatchers("/userlist**").hasAnyRole("ADMIN")
                        .requestMatchers("/addUserForm**").hasAnyRole("ADMIN")
                        .requestMatchers("/confirm-account**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/addProductToCart**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/home");
        return http.build();
    }
}
