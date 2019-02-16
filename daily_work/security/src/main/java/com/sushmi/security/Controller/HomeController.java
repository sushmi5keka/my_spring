package com.sushmi.security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/login"})
    public String login(){
        return "login";
    }

    @GetMapping(value = "/user/u")
    public String user(){
        return "user/user";
    }

    @GetMapping(value = "/admin/h")
    public String home(){
        return "admin/home";
    }

    @GetMapping(value = "/public/r")
    public String regi(){
        return "public/regi";
    }
}
