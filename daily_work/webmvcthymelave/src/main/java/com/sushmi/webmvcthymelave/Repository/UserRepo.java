package com.sushmi.webmvcthymelave.Repository;

import com.sushmi.webmvcthymelave.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}