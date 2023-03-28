package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.DashboardData;
import com.portfolio.webshop_0321.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public double lifeTimeSales() {
        return dashboardRepository.lifeTimeSales();
    }
    //Total profit
    @Override
    public Double lifeTimeProfit() {
        return dashboardRepository.lifeTimeProfit();
    }



    // Today Sale
    //Find 2023.03.27 in sales_sale and sum (sold_product_pieces * sold_product_price) give data to HTML

    // Today profit

}
