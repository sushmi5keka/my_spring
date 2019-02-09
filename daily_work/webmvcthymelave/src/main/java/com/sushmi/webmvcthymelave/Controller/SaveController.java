package com.sushmi.webmvcthymelave.Controller;


import com.sushmi.webmvcthymelave.Entity.Save;

import com.sushmi.webmvcthymelave.Repository.SaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
@Controller
public class SaveController {

    @Autowired
    private SaveRepo repo;

    @GetMapping(value = "/add")
    public String showForm(Save save){
        return "add-page";
    }


    @PostMapping(value = "/add")
        public String save(@Valid Save save, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "add-page";
        }
        this.repo.save(save);
        model.addAttribute("save",new Save());
        model.addAttribute("successfully","Congratulation");
        return "add-page";
        }

    @GetMapping(value = "/edit")
    public String editForm(Save save){
        return "edit-page";
    }

    @PostMapping(value = "/edit")
    public String edit(@Valid Save save, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "edit-page";
        }
        this.repo.save(save);
        model.addAttribute("save",new Save());
        return "edit-page";
    }

//    @GetMapping(value = "/add")
//    public String ShowForm(Save save){
//        return "add-page";
//    }

    @GetMapping(value = "/result")
    public String showResult(){
        return "result";
    }
    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("list",this.repo.findAll());
        return "form";
    }

    public String del(@PathVariable("id") Long id){
        if(id != null){
            this.repo.deleteById(id);

        }
        return "redirect:/";
    }


//
//    @GetMapping(value = "/")
//    public ModelAndView save(){
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("save",new Save());
//        mv.setViewName("saveindex");
//        return mv;
//    }
//
//    @PostMapping(value = "/")
//    public ModelAndView add(@Valid Save save){
//        ModelAndView mv = new ModelAndView();
//
//        if (save.getName() != null){
//            this.repo.save(save);
//            mv.addObject("ssmsg","successfully saved");
//            mv.addObject("save",new Save());
//        }
//        mv.setViewName("saveindex");
//        return mv;
//    }

}
