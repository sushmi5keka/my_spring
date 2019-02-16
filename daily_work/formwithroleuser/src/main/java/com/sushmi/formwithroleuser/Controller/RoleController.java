package com.sushmi.formwithroleuser.Controller;

import com.sushmi.formwithroleuser.Entity.Role;
import com.sushmi.formwithroleuser.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RoleController {

    @Autowired
    private RoleRepo repo;

    @GetMapping(value = "/add-role")
    public String viewAddRole(Role role){
        return "addRole";
    }

    @PostMapping(value = "/add-role")
    public String addRole(@Valid Role role, BindingResult result, Model model){
        if (result.hasErrors()){
            return "addRole";
        }else {
            if (role != null){
                Role role1 = this.repo.findByRolename(role.getRolename());
                if (role1 != null){
                    model.addAttribute("exMsg","role name already exist...");
                }else {
                    this.repo.save(role);
                    model.addAttribute("role",new Role());
                    model.addAttribute("scMsg","role name add successfull...");
                }
            }
        }
        return "addRole";
    }

    @GetMapping(value = "/role-list")
    public String viewRoleList(Model model){
        model.addAttribute("rlist",this.repo.findAll());
        return "listOfRole";
    }


}
