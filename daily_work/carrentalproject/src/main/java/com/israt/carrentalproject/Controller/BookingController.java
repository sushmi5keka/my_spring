package com.israt.carrentalproject.Controller;




import com.israt.carrentalproject.Entity.Booking;
import com.israt.carrentalproject.Entity.Car;
import com.israt.carrentalproject.Entity.User;
import com.israt.carrentalproject.Repo.BookingRepo;
import com.israt.carrentalproject.Repo.CarRepo;
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
@RequestMapping(value = "/booking/")
public class BookingController {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private CarRepo carRepo;

    @GetMapping(value = "add/{id}")
    public String viewAdd(Model model,@PathVariable("id") Long id) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("carno",carRepo.getOne(id));
        model.addAttribute("carlist",carRepo.findAll());
        return "bookings/add";
    }


    @PostMapping(value = "add/{id}")
    public String add(@Valid Booking booking, BindingResult result, Model model, @PathVariable("id") Long id, @Valid Car car){
        if(result.hasErrors()){
            model.addAttribute("rejectMsg","Somthing is wrong");
            model.addAttribute("carno",carRepo.getOne(id));
            model.addAttribute("carlist",carRepo.findAll());
            return "bookings/add";
        }else{
//            Optional<Car> c = this.carRepo.findById(car.getId());
//            car.setFilePath(c.get().getFilePath());
//            car.setFarePerDay(c.get().getFarePerDay());
            this.bookingRepo.save(booking);
            model.addAttribute("booking",new Booking());
            model.addAttribute("successMsg","Successfully Saved!");
            model.addAttribute("carno",carRepo.getOne(id));
            model.addAttribute("carlist",carRepo.findAll());
        }

        return "bookings/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("booking",bookingRepo.getOne(id));
        model.addAttribute("carno",carRepo.getOne(id));
        model.addAttribute("carlist",carRepo.findAll());
        return "bookings/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Booking booking, BindingResult result, Model model,@PathVariable("id") Long id) {
        if (result.hasErrors()) {
            model.addAttribute("rejectMsg","Somthing is wrong");
            model.addAttribute("carno",carRepo.getOne(id));
            model.addAttribute("carlist",carRepo.findAll());
            return "bookings/edit";
        } else {
//            booking.setId(id);
            this.bookingRepo.save(booking);
            model.addAttribute("booking",new Booking());
            model.addAttribute("successMsg","Successfully Saved!");
            model.addAttribute("carno",carRepo.getOne(id));
            model.addAttribute("carlist",carRepo.findAll());
            return "redirect:/booking/list";
        }
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.bookingRepo.deleteById(id);
        }
        return "redirect:/booking/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list",this.bookingRepo.findAll());
        return "bookings/list";
    }

}
