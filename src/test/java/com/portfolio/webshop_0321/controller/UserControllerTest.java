package com.portfolio.webshop_0321.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.webshop_0321.entity.Role;
import com.portfolio.webshop_0321.entity.User;
import com.portfolio.webshop_0321.repository.UserRepository;
import com.portfolio.webshop_0321.service.UserService;
import com.portfolio.webshop_0321.util.TbConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;
    @Test
    public  void test_getContactList() throws Exception {
        List<User> userList = Arrays.asList(new User(23L,"John","Doe","john@gmail.com",
                "Password","M",true, Arrays.asList(new Role(TbConstants.Roles.USER))),
        new User(10L,"John","Doe","john@gmail.com",
                "Password","M",true, Arrays.asList(new Role(TbConstants.Roles.USER))));

        when(userService.findAll()).thenReturn(userList);
        mockMvc.perform(get("/userlist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}