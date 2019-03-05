package com.israt.carrentalproject.Configaretion;

import com.israt.carrentalproject.Entity.User;
import com.israt.carrentalproject.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepo.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);
        optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("User name Not Found..."));
        return optionalUser
                .map(CustomerUserDetails::new).get();
    }
}
