package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.dto.UserDto;
import com.portfolio.webshop_0321.entity.*;
import com.portfolio.webshop_0321.repository.ConfirmationTokenRepository;
import com.portfolio.webshop_0321.repository.RoleRepository;
import com.portfolio.webshop_0321.repository.UserRepository;
import com.portfolio.webshop_0321.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private EmailService emailService;// email repository?
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findID(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void saveUserDto(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null) {
            role = roleRepository.save(new Role(TbConstants.Roles.USER));
        }

        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getGender(),
                Arrays.asList(role));
        saveUser(user);
    }

    @Override
    public ResponseEntity<?> saveUser(User user) {
        if (Boolean.TRUE.equals(userRepository.existsByUserEmail(user.getUserEmail()))) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUserEmail());
        mailMessage.setSubject("Complete Your Registration at RP's Shop!");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8082/confirm-account?token=" + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);
        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ModelAndView confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userRepository.findByUserEmailIgnoreCase(token.getUserEntity().getUserEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return new ModelAndView("email-verification-ok");
        }
        return new ModelAndView("email-verification-nok");
    }

    @Override
    public void createAdmin() {
        if (Boolean.FALSE.equals(userRepository.existsByUserEmail("admin@admin.com"))) {
            User admin = new User();
            admin.setUserEmail("admin@admin.com");
            admin.setUserFirstName("admin");
            admin.setUserLastName("admin");
            admin.setUserGender("X");
            admin.setUserPassword(passwordEncoder.encode("Password"));
            admin.setEnabled(true);
            admin.setRoles(Arrays.asList(roleRepository.save(new Role(TbConstants.Roles.ADMIN))));
            userRepository.save(admin);
        }
    }

    @Override
    public void createFirstUser() {
        if (Boolean.FALSE.equals(userRepository.existsByUserEmail("user@user.com"))) {
            User user = new User();
            user.setUserEmail("user@user.com");
            user.setUserFirstName("John");
            user.setUserLastName("Doe");
            user.setUserGender("M");
            user.setUserPassword(passwordEncoder.encode("Password"));
            user.setEnabled(true);
            user.setRoles(Arrays.asList(roleRepository.save(new Role(TbConstants.Roles.USER))));
            userRepository.save(user);
        }
    }

}
