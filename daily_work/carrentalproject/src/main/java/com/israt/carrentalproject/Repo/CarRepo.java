package com.israt.carrentalproject.Repo;

import com.israt.carrentalproject.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {
}
