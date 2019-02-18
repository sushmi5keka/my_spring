package com.ex.securityloginwithmysql.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/user")
    public  String userView(){
        return  "user/user";
    }

    @GetMapping(value = "/secure")
    public  String secureView() {
        return  "secure/secure";
    }

    @GetMapping(value = "/admin")
    public  String adminView(){

        return  "admin/admin";
    }

    @GetMapping(value = "/super_admin")
    public  String superaadminView(){

        return  "superadmin/superadmin";
    }

}
