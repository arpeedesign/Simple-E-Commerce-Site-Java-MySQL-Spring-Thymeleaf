package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.DashboardData;
import com.portfolio.webshop_0321.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardDataServiceImlp implements DashboardDataService {

    @Autowired
    DashboardRepository dashboardRepository;

    @Override
    public void saveDashboardData(DashboardData dashboardData) {
        dashboardRepository.save(dashboardData);
    }
}
