package com.israt.carrentalproject.Repo;

import com.israt.carrentalproject.Entity.BookingSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingSummaryRepo extends JpaRepository<BookingSummary,Long> {
}
