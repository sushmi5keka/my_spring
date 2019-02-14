package com.ex.form.Controller;

import com.ex.form.Entity.Student;
import com.ex.form.Repository.RoleRepo;
import com.ex.form.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private StudentRepo repo;

    @Autowired
    private RoleRepo roleRepo;

    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("list",this.repo.findAll());
        this.repo.findAll().forEach((c)-> {
            System.out.println(c.toString());
        });
        return "index";
    }

    @GetMapping(value = "/add")
    public String showForm(Student student,Model model){
        model.addAttribute("roleList",this.roleRepo.findAll());
        return "create";
    }

    @PostMapping(value = "/add")
    public String save(@Valid Student student, BindingResult bindingResult,
                       Model model){
        if (bindingResult.hasErrors()){
            return "create";
        }else {
            student.setRegiDate(new Date());
            try{
                this.repo.save(student);
                model.addAttribute("student", new Student());
                model.addAttribute("roleList",this.roleRepo.findAll());
                model.addAttribute("msg", "Congratulation !!!");
            }catch (Exception e){
                e.getStackTrace();
            }

        }
        model.addAttribute("roleList",this.roleRepo.findAll());
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("student",this.repo.getOne(id));
        return "update";
    }

    @PostMapping("/edit/{id}")
    public String updateForm(@Valid Student student, BindingResult bindingResult
                       ){
        if (bindingResult.hasErrors()){
            return "update";
        }else {
            this.repo.save(student);
        }
        return "redirect:/";
    }

    @GetMapping("/del/{id}")
    public String deleteForm(@PathVariable("id") Long id){
        if(id != null){
            this.repo.deleteById(id);
        }
        return "redirect:/";
    }

}
