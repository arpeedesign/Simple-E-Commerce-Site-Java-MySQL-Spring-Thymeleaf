package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.DashboardData;
import com.portfolio.webshop_0321.entity.Order;
import com.portfolio.webshop_0321.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardDataServiceImlp implements DashboardDataService {

    @Autowired
    DashboardRepository dashboardRepository;
    @Autowired
    OrderService orderService;

    @Override
    public void saveDashboardData(DashboardData dashboardData) {
        dashboardRepository.save(dashboardData);
    }

    @Override
    public List<DashboardData> findAllDashboardData() {
        return dashboardRepository.findAllDashboardData();
    }

    @Override
    public int lifeTimeSales() {
        if(dashboardRepository.lifeTimeSales()== null){
            return 0;
        }
        return dashboardRepository.lifeTimeSales().intValue();
    }
    //Total profit
    @Override
    public int lifeTimeProfit() {
        if(dashboardRepository.lifeTimeProfit()==null){
            return 0;
        }
        return dashboardRepository.lifeTimeProfit().intValue();
    }

    @Override
    public Double todaySales() {
        return dashboardRepository.todaySales();
    }

    @Override
    public Double todayProfit() {
        return dashboardRepository.todayProfit();
    }

    @Override
    public int selectedDaySales(LocalDate date) {
        if(dashboardRepository.selectedDaySales(date) == null){
            return 0;
        }
        return dashboardRepository.selectedDaySales(date).intValue();
    }

    @Override
    public int selectedDayProfit(LocalDate date) {
        if(dashboardRepository.selectedDayProfit(date) == null){
            return 0;
        }
        return dashboardRepository.selectedDayProfit(date).intValue();
    }

    @Override
    public Double orderedCartItemsTotalValue() {
        List<Order> list = orderService.findAllOrder();
        double orderedCartItemsTotalValue=0.0;
        for(Order i:list){
            orderedCartItemsTotalValue=orderedCartItemsTotalValue+i.getOrderedCartTotalValue();
        }
        return BigDecimal.valueOf(orderedCartItemsTotalValue).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
