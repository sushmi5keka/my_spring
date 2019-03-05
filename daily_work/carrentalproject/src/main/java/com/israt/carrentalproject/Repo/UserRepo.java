package com.israt.carrentalproject.Repo;

import com.israt.carrentalproject.Entity.Role;
import com.israt.carrentalproject.Entity.User;
import com.israt.carrentalproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUserNameOrEmail(String userName, String email);

    User findByUserName(String username);

    List<User>findByRoles(Set<Role> roles);
    //////////////////////////

//    User findByNameAndUserName(String name, String username);
//
//

}