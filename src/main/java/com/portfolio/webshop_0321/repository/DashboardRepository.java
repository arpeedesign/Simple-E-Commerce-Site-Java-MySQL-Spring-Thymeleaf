package com.portfolio.webshop_0321.repository;

import com.portfolio.webshop_0321.entity.DashboardData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends CrudRepository<DashboardData, Long> {
}
