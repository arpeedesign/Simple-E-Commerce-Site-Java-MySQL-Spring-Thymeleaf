package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.DashboardData;
import com.portfolio.webshop_0321.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardDataServiceImlp implements DashboardDataService {

    @Autowired
    DashboardRepository dashboardRepository;

    @Override
    public void saveDashboardData(DashboardData dashboardData) {
        dashboardRepository.save(dashboardData);
    }

    @Override
    public List<DashboardData> findAllDashboardData() {
        return dashboardRepository.findAllDashboardData();
    }

    // Total Sale
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

    // Today Sale
    //Find 2023.03.27 in sales_sale and sum (sold_product_pieces * sold_product_price) give data to HTML

    // Today profit

}
