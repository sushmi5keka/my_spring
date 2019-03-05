package com.israt.carrentalproject.Repo;

import com.israt.carrentalproject.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
