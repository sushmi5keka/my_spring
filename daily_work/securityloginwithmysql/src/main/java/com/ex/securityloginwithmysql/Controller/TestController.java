package com.ex.securityloginwithmysql.Controller;

import com.ex.securityloginwithmysql.Entity.User;
import com.ex.securityloginwithmysql.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class TestController {

    @Autowired
    private UserRepo repo;

    @ResponseBody
    @GetMapping(value = "/test")
    public User display() {
        return repo.findByNameAndUserName("Ria", "ria");
    }
}
