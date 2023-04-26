package com.portfolio.webshop_0321.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.webshop_0321.entity.DashboardData;
import com.portfolio.webshop_0321.entity.Product;
import com.portfolio.webshop_0321.repository.DashboardRepository;
import com.portfolio.webshop_0321.service.DashboardDataService;
import com.portfolio.webshop_0321.service.OrderService;
import com.portfolio.webshop_0321.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DashboardController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {
    @MockBean
    private DashboardDataService dashboardDataService;
    @MockBean
    private ProductService productService;
    @MockBean
    private OrderService orderService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTodayProfit() throws Exception {
        Double profit = 100.0;
        when(dashboardDataService.todayProfit()).thenReturn(profit);

        MvcResult mvcResult = mockMvc.perform(get("/todayProfit"))
                .andExpect(status().isOk())
                .andReturn();
        int result = Integer.parseInt(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(result, profit.intValue());
    }
    @Test
    public void testTodaySales() throws Exception {
        Double sales = 100.0;
        when(dashboardDataService.todaySales()).thenReturn(sales);

        MvcResult mvcResult = mockMvc.perform(get("/todaySales"))
                .andExpect(status().isOk())
                .andReturn();
        int result = Integer.parseInt(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(result, sales.intValue());
    }

}