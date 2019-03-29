package com.israt.carrentalproject.Repo;

import com.israt.carrentalproject.Entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<ContactUs,Long> {
}
