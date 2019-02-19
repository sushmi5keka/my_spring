package com.ex.securityloginwithmysql.Controller;

import com.ex.securityloginwithmysql.Entity.Role;
import com.ex.securityloginwithmysql.Entity.User;
import com.ex.securityloginwithmysql.Repo.RoleRepo;
import com.ex.securityloginwithmysql.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/user")
public class UserConteoller {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @GetMapping(value = "/index")
    public String index(Model model){
        model.addAttribute("list",this.repo.findAll());
        this.repo.findAll().forEach((c)-> {
            System.out.println(c.toString());
        });
        return "usercrud/index";
    }

    @GetMapping(value = "/add")
    public String showForm(User user,Model model){
        model.addAttribute("roleList",this.roleRepo.findAll());
        return "usercrud/create";
    }

    @PostMapping(value = "/add")
    public String save(@Valid User user, BindingResult bindingResult,
                       Model model){
        if (bindingResult.hasErrors()){
            return "usercrud/create";
        }else {

            try{
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                this.repo.save(user);
                model.addAttribute("user", new User());
                model.addAttribute("roleList",this.roleRepo.findAll());
                model.addAttribute("msg", "Congratulation !!!");
            }catch (Exception e){
                e.getStackTrace();
            }

        }
        model.addAttribute("roleList",this.roleRepo.findAll());
        return "usercrud/create";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("user",this.repo.getOne(id));
        model.addAttribute("roleList",this.roleRepo.findAll());
        return "usercrud/update";
    }

    @PostMapping("/edit/{id}")
    public String updateForm(@Valid User user, BindingResult bindingResult,
    Model model){
        if (bindingResult.hasErrors()){
            return "usercrud/update";
        }else {
            this.repo.save(user);
            model.addAttribute("roleList",this.roleRepo.findAll());
        }
        return "redirect:/user/index";
    }

    @GetMapping("/del/{id}")
    public String deleteForm(@PathVariable("id") Long id){
        if(id != null){
            this.repo.deleteById(id);
        }
        return "redirect:/user/index";
    }

//    @GetMapping(value = "/user-save")
//    public  String  saveUser(){
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(2L));
//        roles.add(new Role(4L));
//        User user = new User("Sushmi","superadmin",passwordEncoder.encode("123456"),"sushmi@gmail.com",true,roles);
//        repo.save(user);
//
//
//        Set<Role> roles2 = new HashSet<>();
//        roles2.add(new Role(3L));
//        User user2 = new User("Moly","admin",passwordEncoder.encode("123456"),"Moly@gmail.com",true,roles2);
//        repo.save(user2);
//
//        Set<Role> roles3 = new HashSet<>();
//        roles3.add(new Role(4L));
//        User user3 = new User("Rozina","user",passwordEncoder.encode("123456"),"Rozina@gmail.com",true,roles3);
//        repo.save(user3);
//
//        return "success";
//    }

}
