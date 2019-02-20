package com.example.templeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControler {

    @GetMapping(value = "/layout")
    public  String display(){
        return "layout";
    }
}
