package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.DashboardData;

import java.util.Date;
import java.util.List;

public interface DashboardDataService {

    void saveDashboardData(DashboardData dashboardData);
     List<DashboardData> findAllDashboardData();


    double lifeTimeSales();

    Double lifeTimeProfit();

    Double todaySales();

    Double todayProfit();
}
