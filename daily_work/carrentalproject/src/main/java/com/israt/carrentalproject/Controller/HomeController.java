package com.israt.carrentalproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {



    //////////////////////////////

    @GetMapping(value = "/")
    public String dashbord(){
        return "index";
    }

    @GetMapping(value = "/abouts")
    public String about() {
        return "about";
    }

    @GetMapping(value = "/carsphoto")
    public String cars() {
        return "carsphoto";
    }

    @GetMapping(value = "/map")
    public String map() {
        return "map";
    }



//    @GetMapping(value = "/login")
//    public String login() {
//        return "login";
//    }

    //////////////////////////////


}
