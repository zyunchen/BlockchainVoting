package com.scu275.invoicemanagement.service;


import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.LoginDto;
import com.scu275.invoicemanagement.dto.SignUpDto;
import com.scu275.invoicemanagement.entity.User;
import com.scu275.invoicemanagement.entity.UserRepository;
import com.scu275.invoicemanagement.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public Result<String> signUpUser(SignUpDto signUpDto){
        System.out.println("begin to sign up user-------");

        //use regrex to check if username is a valid email
        String emailRegrex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        
        if(!signUpDto.getUsername().matches(emailRegrex)){
            System.out.println("username is not a valid email");
            return Result.failed("Username is not a valid email");
        }

        // checking for username exists in a database
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            System.out.println("user name exist -------sign up failed");
            return Result.failed("Username is already exist");
        }

        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userRepository.save(user);
        System.out.println("user name not exist -------sign up success");

        Mail mail = new Mail();
        mail.setTo(user.getUsername());
        mail.setSubject("Register successfully");
        mail.setContent("Register successfully");
        mailService.sendMail(mail);


        return Result.success("register success");
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not exists by Username");
        }
        return user;
    }
}

