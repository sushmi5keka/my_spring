package com.ex.form.Controller;

import com.ex.form.Entity.Role;
import com.ex.form.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RoleController {

    @Autowired
    private RoleRepo repo;

    @GetMapping(value = "/add-role")
    public String addRoleView(Role role) {
        return "Role/add-role";
    }

    @PostMapping(value = "/add-role")
    public String addRole(@Valid Role role, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Role/add-role";
        } else {
            if (role != null) {
                Role role1 = this.repo.findByRoleName(role.getRoleName());
                if (role1 != null) {
                    model.addAttribute("existMsg", "RoleName is exist...");
                } else {
                    this.repo.save(role);
                    model.addAttribute("role", new Role());
                    model.addAttribute("successMsg", "Already Success");
                }
            }
        }
        return "Role/add-role";
    }

    @GetMapping(value = "/role-list")
    public String roleList(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "Role/list";
    }

    @GetMapping(value = "/edit-role/{id}")
    public String editRoleView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", this.repo.getOne(id));
        return "Role/edit";
    }

    @PostMapping(value = "/edit-role/{id}")
    public String editRole(@Valid Role role,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "Role/edit";
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
        return "redirect:/role-list";
    }

    @RequestMapping(value = "/role-del/{id}", method = RequestMethod.GET)
    public String delRole(@PathVariable("id") Long id) {
        this.repo.deleteById(id);
        return "redirect:/role-list";

    }
}
