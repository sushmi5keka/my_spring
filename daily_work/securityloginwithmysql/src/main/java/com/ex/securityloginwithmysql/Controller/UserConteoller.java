package com.ex.securityloginwithmysql.Controller;

import com.ex.securityloginwithmysql.Entity.Role;
import com.ex.securityloginwithmysql.Entity.User;
import com.ex.securityloginwithmysql.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserConteoller {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/user-save")
    public  String  saveUser(){

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(2L));
        roles.add(new Role(4L));
        User user = new User("Sushmi","superadmin",passwordEncoder.encode("123456"),"sushmi@gmail.com",true,roles);
        repo.save(user);


        Set<Role> roles2 = new HashSet<>();
        roles2.add(new Role(3L));
        User user2 = new User("Moly","admin",passwordEncoder.encode("123456"),"Moly@gmail.com",true,roles2);
        repo.save(user2);

        Set<Role> roles3 = new HashSet<>();
        roles3.add(new Role(4L));
        User user3 = new User("Rozina","user",passwordEncoder.encode("123456"),"Rozina@gmail.com",true,roles3);
        repo.save(user3);

        return "success";
    }

}
