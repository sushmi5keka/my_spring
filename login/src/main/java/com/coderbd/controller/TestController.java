package com.coderbd.controller;

import com.coderbd.entity.User;
import com.coderbd.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
public class TestController {
    @Autowired
    private UserRepo repo;

    @ResponseBody
    @GetMapping(value = "/test")
    public Optional<User> display(){
        return repo.findByUserNameOrEmail("jobayerccc","");
    }
}
