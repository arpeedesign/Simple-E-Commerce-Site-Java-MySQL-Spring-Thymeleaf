package com.portfolio.webshop_0321.repository;

import com.portfolio.webshop_0321.entity.DashboardData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DashboardRepository extends CrudRepository<DashboardData, Long> {

    @Query("SELECT u FROM DashboardData u")
    List<DashboardData> findAllDashboardData();

    @Query(value = "SELECT SUM(sold_product_pieces*sold_product_price) FROM dashboard_data", nativeQuery = true)
    double lifeTimeSales();


}
