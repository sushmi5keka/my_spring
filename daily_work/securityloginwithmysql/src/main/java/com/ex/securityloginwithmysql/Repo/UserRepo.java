package com.ex.securityloginwithmysql.Repo;

import com.ex.securityloginwithmysql.Entity.Role;
import com.ex.securityloginwithmysql.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUserNameOrEmail(String userName, String email);

    User findByUserName(String username);

    //////////////////////////

    User findByNameAndUserName(String name, String username);


}
