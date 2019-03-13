package com.israt.carrentalproject.Controller;


import com.israt.carrentalproject.Entity.Address;

import com.israt.carrentalproject.Repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/address/")
public class AddressController {

@Autowired
    private AddressRepo addressRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("address", new Address());
        return "addresss/add";
    }


    @PostMapping(value = "add")
   public String add(@Valid Address address, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("rejectMsg","Somthing is wrong");
           return "addresss/add";
       }else{
           this.addressRepo.save(address);
           model.addAttribute("address",new Address());
           model.addAttribute("successMsg","Successfully Saved!");
       }

       return "addresss/add";
   }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("address",addressRepo.getOne(id));
        return "addresss/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Address address, BindingResult result, Model model,@PathVariable("id") Long id) {
        if (result.hasErrors()) {
            model.addAttribute("rejectMsg","Somthing is wrong");
            return "addresss/edit";
        } else {
            address.setId(id);
            address.setLocation(address.getLocation());
            this.addressRepo.save(address);
            model.addAttribute("address",new Address());
            model.addAttribute("successMsg","Successfully Saved!");
            return "redirect:/address/list";
        }
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.addressRepo.deleteById(id);
        }
        return "redirect:/address/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list",this.addressRepo.findAll());
        return "addresss/list";
    }

}
