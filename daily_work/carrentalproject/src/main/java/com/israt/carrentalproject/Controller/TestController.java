//package com.israt.carrentalproject.Controller;
//
//import com.israt.carrentalproject.Entity.User;
//import com.israt.carrentalproject.Repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class TestController {
//
//    @Autowired
//    private UserRepo repo;
//
//    @ResponseBody
//    @GetMapping(value = "/test")
//    public User display() {
//        return repo.findByNameAndUserName("Ria", "ria");
//    }
//}
