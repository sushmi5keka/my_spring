package com.israt.carrentalproject.Controller;

import com.israt.carrentalproject.Entity.Address;
import com.israt.carrentalproject.Entity.ContactUs;
import com.israt.carrentalproject.Repo.AddressRepo;
import com.israt.carrentalproject.Repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private ContactRepo contactRepo;

    @GetMapping(value = "/contacts")
    public String viewAdd(Model model) {
        model.addAttribute("contact", new ContactUs());
        return "contact";
    }
    @PostMapping(value = "/contacts")
    public String add(@Valid ContactUs contactUs, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("rejectMsg","Somthing is wrong");
            return "contact";
        }else{
            this.contactRepo.save(contactUs);
            model.addAttribute("contact", new ContactUs());
            model.addAttribute("successMsg","Successfully Saved!");
        }

        return "contact";
    }
}
