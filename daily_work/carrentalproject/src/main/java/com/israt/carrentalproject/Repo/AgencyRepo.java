package com.israt.carrentalproject.Repo;

import com.israt.carrentalproject.Entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepo extends JpaRepository<Agency ,Long> {



}
