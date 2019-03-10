package com.israt.carrentalproject.Repo;


import com.israt.carrentalproject.Entity.Category;
import com.israt.carrentalproject.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);
    boolean existsCategoryByName(String name);

}
