package com.israt.carrentalproject.Controller;

import com.israt.carrentalproject.Entity.User;
import com.israt.carrentalproject.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {



    //////////////////////////////

    @GetMapping(value = "/")
    public String dashbord(){
        return "index";
    }

//    @GetMapping(value = "/user")
//    public String user() {
//        return "userprofile";
//    }
//
//    @GetMapping(value = "/login")
//    public String login() {
//        return "login";
//    }

    //////////////////////////////


}
