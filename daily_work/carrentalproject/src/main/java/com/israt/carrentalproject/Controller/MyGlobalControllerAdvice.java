package com.israt.carrentalproject.Controller;

import com.israt.carrentalproject.Entity.User;
import com.israt.carrentalproject.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyGlobalControllerAdvice {

    @Autowired
    private UserRepo userRepo;

    @ModelAttribute("us")
    public User getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepo.findByUserName(authentication.getName());
        return user;
    }
}
