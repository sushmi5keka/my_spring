package com.sushmi.formwithroleuser.Repo;

import com.sushmi.formwithroleuser.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByRolename(String rolename);
}
