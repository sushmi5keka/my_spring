package com.israt.carrentalproject.Repo;

import com.israt.carrentalproject.Entity.CustomerSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSummaryRepo extends JpaRepository<CustomerSummary,Long> {
}
