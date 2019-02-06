package com.example.hellomvc.Controler;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping(value = "/")
    public String home(){
        return "Hello vaa";
    }

    @GetMapping(value = "/test")
    public String test(){
        return "Hello moo";
    }
}
