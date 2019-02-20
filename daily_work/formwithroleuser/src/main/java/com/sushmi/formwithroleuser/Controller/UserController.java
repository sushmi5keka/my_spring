package com.sushmi.formwithroleuser.Controller;

import com.sushmi.formwithroleuser.Entity.User;
import com.sushmi.formwithroleuser.Repo.RoleRepo;
import com.sushmi.formwithroleuser.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;

    @GetMapping(value = "/")
    public String showUserList(Model model) {
        model.addAttribute("ulist", this.repo.findAll());
        this.repo.findAll().forEach((c) -> {
            System.out.println(c.toString());
        });
        return "index";
    }

    @GetMapping(value = "/add-user")
    public String addUser(Model model) {
        model.addAttribute("roleList", this.roleRepo.findAll());
        return "addUser";
    }

    @PostMapping(value = "/add-user")
    public String viewAddUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addUser";
        } else {
            this.repo.save(user);
            model.addAttribute("user", new User());
            model.addAttribute("roleList", this.roleRepo.findAll());
            model.addAttribute("msg", "Congratulation...");

        }
        model.addAttribute("roleList", this.roleRepo.findAll());
        return "addUser";
    }

}
