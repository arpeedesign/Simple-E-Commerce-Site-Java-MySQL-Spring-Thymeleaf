package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class DashboardDataServiceImlpTest {

    @InjectMocks
    DashboardDataServiceImlp dashboardDataService;

    @Mock
    OrderService orderService;
    private Order order1;
    private Order order2;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testOrderedCartItemsTotalValue() {
        order1 = new Order();
        order1.setOrderedCartTotalValue(10.00);

        order2 = new Order();
        order2.setOrderedCartTotalValue(20.00);
        List<Order> orders = new ArrayList<Order>();
        orders.add(order1);
        orders.add(order2);
        when(orderService.findAllOrder()).thenReturn(orders);
        Double expected = 30.00;
        Double actual = dashboardDataService.orderedCartItemsTotalValue();
        assertEquals(expected, actual, 0.001);
    }
}