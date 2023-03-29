package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.DashboardData;
import com.portfolio.webshop_0321.service.DashboardDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DashboardController {
    @Autowired
    DashboardDataService dashboardDataService;

    @PostMapping("/saveDashboardData")
    public ModelAndView saveProduct(DashboardData dashboardData) {
        ModelAndView mav = new ModelAndView("add-dashboardData-form");
        DashboardData newDashboardData = new DashboardData();
        dashboardDataService.saveDashboardData(dashboardData);
        mav.addObject("dashboardData", newDashboardData);
        return new ModelAndView("redirect:/dashboard");
    }

    @GetMapping("/findAllDashboardData")
    public List <DashboardData> findAllDashboardData() {
        return dashboardDataService.findAllDashboardData();
    }
    @GetMapping("/lifeTimeSales")
    public int lifeTimeSales() {
        Double totalSales=dashboardDataService.lifeTimeSales();
        return totalSales.intValue();
    }
    @GetMapping("/selectedDaySales") // /todaySalesJPQL?date=2023-03-10
    public int selectedDaySales(@RequestParam(required = true) String date) {
        LocalDate selectedDay = LocalDate.parse(date);
        Double selectedDaySales=dashboardDataService.selectedDaySales(selectedDay);
        return selectedDaySales.intValue();
    }
    @GetMapping("/selectedDayProfit") // /todaySalesJPQL?date=2023-03-10
    public int selectedDayProfit(@RequestParam(required = true) String date) {
        LocalDate selectedDay = LocalDate.parse(date);
        Double selectedDayProfit=dashboardDataService.selectedDayProfit(selectedDay);
        return selectedDayProfit.intValue();
    }
    @GetMapping("/lifeTimeProfit")
    public int lifeTimeProfit() {
        Double totalProfit=dashboardDataService.lifeTimeProfit();
        return totalProfit.intValue();
    }
    @GetMapping("/todaySales")
    public int todaySales() {
        Double todaySales=dashboardDataService.todaySales();
        return todaySales.intValue();
    }
    @GetMapping("/todayProfit")
    public int todayProfit() {
        Double todayProfit=dashboardDataService.todayProfit();
        return todayProfit.intValue();
    }

}
