package com.israt.carrentalproject.Controller;

import com.israt.carrentalproject.Entity.Role;
import com.israt.carrentalproject.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleRepo repo;

    @GetMapping(value = "/add-role")
    public String viewAddRole(Role role) {
        return "role/add-role";
    }

    @PostMapping(value = "/add-role")
    public String addRole(@Valid Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "role/add-role";
        } else {
            if (role != null) {
                Role role1 = this.repo.findByRoleName(role.getRoleName());
                if (role1 != null) {
                    model.addAttribute("exMsg", "role name already exist...");
                } else {
                    this.repo.save(role);
                    model.addAttribute("role", new Role());
                    model.addAttribute("successMsg", "role name add successfull...");
                }
            }
        }
        return "role/add-role";
    }

    @GetMapping(value = "/role-list")
    public String viewRoleList(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "role/list";
    }


    @GetMapping(value = "/edit-role/{id}")
    public String editRoleView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", this.repo.getOne(id));
        return "role/edit";
    }

    @PostMapping(value = "/edit-role/{id}")
    public String editRole(@Valid Role role,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "role/edit";
        } else {
            if (role != null) {
                Role role1 = this.repo.findByRoleName(role.getRoleName());
                if (role1 != null) {
                    model.addAttribute("existMsg", "RoleName is exist...");
                } else {
                    this.repo.save(role);
                    model.addAttribute("role", "new Role()");
                    model.addAttribute("successMsg", "Already Success");
                }
            }
        }
        return "redirect:/role/role-list";
    }

    @RequestMapping(value = "/role-del/{id}", method = RequestMethod.GET)
    public String delRole(@PathVariable("id") Long id) {
        this.repo.deleteById(id);
        return "redirect:/role/role-list";

    }

//    @GetMapping(value = "/role-save")
//    public String  saveRole(){
//
//        Role role= new Role();
//        role.setRoleName("SUPERADMIN");
//        repo.save(role);
//
//        Role role1= new Role();
//        role1.setRoleName("ADMIN");
//        repo.save(role1);
//
//        Role role2= new Role();
//        role2.setRoleName("USER");
//        repo.save(role2);
//
//        return "success";
//    }
}
