package com.israt.cartemplate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "userprofile";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
