package com.parkease.auth.service;

import com.parkease.auth.entity.User;
import com.parkease.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerUserDetails implements UserDetailsService {



    @Autowired
    private UserRepository userRepo;



    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }

        return new org.springframework.security.core.userdetails.User(
                 user.getUsername(),
                user.getPassword(),new ArrayList<>()
        );


    }

}
