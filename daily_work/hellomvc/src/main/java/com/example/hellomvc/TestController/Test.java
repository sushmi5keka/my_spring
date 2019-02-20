package com.example.hellomvc.TestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping(value = "/tt")
    public String test(@RequestParam(value = "ms", required = true, defaultValue = " hello java") String msg) {
        return msg;
    }
}
