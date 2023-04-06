package com.portfolio.webshop_0321.repository;

import com.portfolio.webshop_0321.entity.DashboardData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DashboardRepository extends CrudRepository<DashboardData, Long> {

    @Query("SELECT u FROM DashboardData u")
    List<DashboardData> findAllDashboardData();

    @Query(value = "SELECT SUM(sold_product_pieces*sold_product_price) FROM dashboard_data", nativeQuery = true)
    Double lifeTimeSales();

    @Query(value = "SELECT SUM(sold_product_pieces*(sold_product_price-sold_product_cog)) FROM dashboard_data", nativeQuery = true)
    Double lifeTimeProfit();
    @Query(value = "SELECT SUM(sold_product_pieces*sold_product_price) FROM dashboard_data where sales_date='2023-03-10'", nativeQuery = true)
    Double todaySales();
    @Query(value = "SELECT SUM(sold_product_pieces*(sold_product_price-sold_product_cog)) FROM dashboard_data where sales_date='2023-03-10'", nativeQuery = true)
    Double todayProfit();
    @Query(value = "SELECT SUM(sold_product_pieces*sold_product_price) FROM dashboard_data x where x.sales_date=?1", nativeQuery = true)
    Double selectedDaySales(LocalDate date);
    @Query(value = "SELECT SUM(sold_product_pieces*(sold_product_price-sold_product_cog)) FROM dashboard_data x where x.sales_date=?1", nativeQuery = true)
    Double selectedDayProfit(LocalDate date);
}
