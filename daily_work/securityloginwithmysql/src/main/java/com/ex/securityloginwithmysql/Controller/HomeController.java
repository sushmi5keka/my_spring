package com.ex.securityloginwithmysql.Controller;

import com.ex.securityloginwithmysql.Entity.User;
import com.ex.securityloginwithmysql.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepo repo;

    @GetMapping(value = "/u")
    public String userView() {
        return "user/user";
    }

    @GetMapping(value = "/secure")
    public String secureView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("usernam", auth.getName());
        User user = repo.findByUserName(auth.getName());
        model.addAttribute("name", user.getName());
        return "secure/secure";
    }

    @GetMapping(value = "/admin")
    public String adminView() {

        return "admin/admin";
    }

    @GetMapping(value = "/guest")
    public String guestView() {

        return "guest/guest";
    }

    @GetMapping(value = "/super_admin")
    public String superaadminView() {

        return "superadmin/superadmin";
    }


}
