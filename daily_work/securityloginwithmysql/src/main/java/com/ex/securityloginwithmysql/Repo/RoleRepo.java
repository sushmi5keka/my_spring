package com.ex.securityloginwithmysql.Repo;

import com.ex.securityloginwithmysql.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
