package com.israt.carrentalproject.Controller;




import com.israt.carrentalproject.Entity.*;
import com.israt.carrentalproject.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookingSummaryRepo bookingSummaryRepo;

    @Autowired
    private CustomerSummaryRepo customerSummaryRepo;

    @GetMapping(value = "add/{id}")
    public String viewAdd(Model model,@PathVariable("id") Long id) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("carno",carRepo.getOne(id));
        model.addAttribute("carlist",carRepo.findAll());
        model.addAttribute("userlist",userRepo.findAll());
        return "bookings/add";
    }


    @PostMapping(value = "add/{id}")
    public String add(@Valid Booking booking,@Valid BookingSummary bookingSum, BindingResult result, Model model, @PathVariable("id") Long id, @Valid Car car){
        if(result.hasErrors()){
            model.addAttribute("rejectMsg","Somthing is wrong");
            model.addAttribute("carno",carRepo.getOne(id));
            model.addAttribute("carlist",carRepo.findAll());
            model.addAttribute("userlist",userRepo.findAll());
//            model.addAttribute("bookinglist",bookingSummaryRepo.findAll());
            return "bookings/add";
        }else{
            Car car1= carRepo.getOne(id);

            booking.setFarePerDay(car1.getFarePerDay());
            booking.setFilePath(car1.getFilePath());
            booking.setFileName(car1.getFileName());
            booking.setFileSize(car1.getFileSize());
            booking.setFileExtension(car1.getFileExtension());

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            booking.setCustomer(userRepo.findByUserName(authentication.getName()));

            this.bookingRepo.save(booking);

            model.addAttribute("booking",new Booking());
            model.addAttribute("successMsg","Successfully Saved!");
            model.addAttribute("carno",carRepo.getOne(id));
            model.addAttribute("carlist",carRepo.findAll());


            BookingSummary bookingSummary = new BookingSummary(booking.getTotalFareAmount(), booking.getAdvanceFareAmount(), booking.getDueFareAmount(),booking);
            bookingSummary.setTotalFareAmount(booking.getTotalFareAmount());
            bookingSummary.setAdvanceFareAmount(booking.getAdvanceFareAmount());
            bookingSummary.setDueFareAmount(booking.getDueFareAmount());
            bookingSummary.setCollectedAmount(booking.getTotalFareAmount()-booking.getDueFareAmount());
            bookingSummaryRepo.save(bookingSummary);
            model.addAttribute("booking",new Booking());
//            model.addAttribute("bookinglist",bookingSummaryRepo.findAll());
            model.addAttribute("successMsg", "Congratulations! Data save sucessfully");


//            CustomerSummary customerSummary = new CustomerSummary();
//            customerSummary.setTotalFareAmount(booking.getTotalFareAmount());
//            customerSummary.setAdvanceFareAmount(booking.getAdvanceFareAmount());
//            customerSummary.setDueFareAmount(booking.getDueFareAmount());
//            customerSummary.setNoOfBooking(booking.);
//            customerSummary.setFirstBookingDate(booking.);
//            customerSummary.setLastBookingDate(booking.);
//            model.addAttribute("userlist",userRepo.findAll());
//            this.customerSummaryRepo.save(customerSummary);


        }

        return "bookings/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("booking",bookingRepo.getOne(id));
        model.addAttribute("carno",carRepo.getOne(id));
        model.addAttribute("carlist",carRepo.findAll());
        model.addAttribute("userlist",userRepo.findAll());
        return "bookings/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Booking booking, BindingResult result, Model model,@PathVariable("id") Long id,@Valid Car car) {
        if (result.hasErrors()) {
            model.addAttribute("rejectMsg","Somthing is wrong");
            model.addAttribute("carno",carRepo.getOne(id));
            model.addAttribute("carlist",carRepo.findAll());
            model.addAttribute("userlist",userRepo.findAll());
            return "bookings/edit";
        } else {
            Car car1= carRepo.getOne(id);

            booking.setFarePerDay(car1.getFarePerDay());
            booking.setFilePath(car1.getFilePath());
            booking.setFileName(car1.getFileName());
            booking.setFileSize(car1.getFileSize());
            booking.setFileExtension(car1.getFileExtension());

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            booking.setCustomer(userRepo.findByUserName(authentication.getName()));

            this.bookingRepo.save(booking);
            model.addAttribute("booking",new Booking());
            model.addAttribute("successMsg","Successfully Saved!");
            model.addAttribute("carno",carRepo.getOne(id));
            model.addAttribute("carlist",carRepo.findAll());


            BookingSummary bookingSummary = new BookingSummary(booking.getTotalFareAmount(), booking.getAdvanceFareAmount(), booking.getDueFareAmount(),booking);
            bookingSummary.setTotalFareAmount(booking.getTotalFareAmount());
            bookingSummary.setAdvanceFareAmount(booking.getAdvanceFareAmount());
            bookingSummary.setDueFareAmount(booking.getDueFareAmount());
            bookingSummary.setCollectedAmount(booking.getTotalFareAmount()-booking.getDueFareAmount());
            bookingSummaryRepo.save(bookingSummary);
            model.addAttribute("booking",new Booking());
//            model.addAttribute("bookinglist",bookingSummaryRepo.findAll());
            model.addAttribute("successMsg", "Congratulations! Data save sucessfully");

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

    @GetMapping(value = "summary")
    public String bsummary(Model model){
        model.addAttribute("summarys",this.bookingSummaryRepo.findAll());
        return "bookings/bsummary";
    }
}
