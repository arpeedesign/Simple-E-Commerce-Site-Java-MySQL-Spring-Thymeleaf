package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.DashboardData;

import java.time.LocalDate;
import java.util.List;

public interface DashboardDataService {

    void saveDashboardData(DashboardData dashboardData);

    List<DashboardData> findAllDashboardData();

    int lifeTimeSales();

    int lifeTimeProfit();

    Double todaySales();

    Double todayProfit();

    int selectedDaySales(LocalDate date);

    int selectedDayProfit(LocalDate date);
    Double orderedCartItemsTotalValue();
}
