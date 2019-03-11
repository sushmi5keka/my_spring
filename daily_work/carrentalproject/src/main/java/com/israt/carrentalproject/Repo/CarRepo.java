package com.israt.carrentalproject.Repo;

import com.israt.carrentalproject.Entity.Car;
import com.israt.carrentalproject.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {
    Optional<Car> findByCarModel(String carModel);
    boolean existsCarByCarModel(String carModel);
}
