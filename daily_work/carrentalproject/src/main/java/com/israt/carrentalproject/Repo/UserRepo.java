package com.israt.carrentalproject.Repo;

import com.israt.carrentalproject.Entity.Role;
import com.israt.carrentalproject.Entity.User;
import com.israt.carrentalproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUserNameOrEmail(String userName, String email);
//    Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String email);
    List<User> findAllByRoles(Set<Role> roles);
    boolean existsByEmail(String email);
    User findByConfirmationToken(String token);
    User findByUserName(String username);
    //////////////////////////

//    User findByNameAndUserName(String name, String username);
//
//
//@Modifying
//@Query("update user u set u.password = ?1, u.tokenExpired=?2 where u.email = ?3")
//int setPasswordAndTokenExpiredByEmail(String password,boolean tokenExpired, String email);
//
//@Modifying
//@Query("update user u set u.tokenExpired=?1 where u.email = ?2")
//int setTokenExpiredByEmail(boolean tokenExpired, String email);

}
