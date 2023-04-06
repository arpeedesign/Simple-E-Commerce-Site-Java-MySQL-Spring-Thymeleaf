package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.DashboardData;
import com.portfolio.webshop_0321.service.DashboardDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DashboardController {
    private static final String DASHBOARD = "dashboard";
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

    @GetMapping("/dashboard")
    public ModelAndView selectedDayData(@RequestParam(required = false) String date) {
        ModelAndView mav = new ModelAndView(DASHBOARD);
        if (date == null || "".equals(date)) {
            date = LocalDate.now().toString();
            String today = LocalDate.now().toString();
            mav.getModel().put("today", today);
        }
        mav.getModel().put("today", date);
        mav.getModel().put("totalSales", dashboardDataService.lifeTimeSales());
        mav.getModel().put("totalProfit", dashboardDataService.lifeTimeProfit());
        LocalDate selectedDay = LocalDate.parse(date);
        mav.getModel().put("selectedDayProfit", dashboardDataService.selectedDayProfit(selectedDay));
        mav.getModel().put("selectedDaySales", dashboardDataService.selectedDaySales(selectedDay));
        return mav;
    }

    @GetMapping("/selectedDaySales")
    public ModelAndView selectedDaySales(@RequestParam String date) {
        LocalDate selectedDay = LocalDate.parse(date);
        int selectedDaySales = dashboardDataService.selectedDaySales(selectedDay);
        ModelAndView mav = new ModelAndView(DASHBOARD);
        mav.getModel().put("selectedDaySales", selectedDaySales);
        return mav;
    }

    @GetMapping("/selectedDayProfit")
    public ModelAndView selectedDayProfit(@RequestParam String date) {
        LocalDate selectedDay = LocalDate.parse(date);
        int selectedDayProfit = dashboardDataService.selectedDayProfit(selectedDay);
        ModelAndView mav = new ModelAndView(DASHBOARD);
        mav.getModel().put("selectedDayProfit", selectedDayProfit);
        return mav;
    }

    @GetMapping("/findAllDashboardData")
    public List<DashboardData> findAllDashboardData() {
        return dashboardDataService.findAllDashboardData();
    }

    @GetMapping("/lifeTimeSales")
    public ModelAndView lifeTimeSales(Model model) {
        model.addAttribute("totalSales", dashboardDataService.lifeTimeSales());
        return new ModelAndView("lifeTimeSales");
    }

    @GetMapping("/lifeTimeProfit")
    public ModelAndView lifeTimeProfit(Model model) {
        model.addAttribute("totalProfit", dashboardDataService.lifeTimeProfit());
        return new ModelAndView("lifeTimeSales");
    }


    @GetMapping("/todaySales")
    public int todaySales() {
        Double todaySales = dashboardDataService.todaySales();
        return todaySales.intValue();
    }

    @GetMapping("/todayProfit")
    public int todayProfit() {
        Double todayProfit = dashboardDataService.todayProfit();
        return todayProfit.intValue();
    }

}
