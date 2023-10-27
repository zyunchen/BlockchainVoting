package com.scu275.invoicemanagement.service;


import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.LoginDto;
import com.scu275.invoicemanagement.dto.SignUpDto;
import com.scu275.invoicemanagement.entity.User;
import com.scu275.invoicemanagement.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public Result<String> signUpUser(SignUpDto signUpDto){
        System.out.println("begin to sign up user-------");

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

        return Result.success("register success");
    }

    public Result<String> login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Result.success("User login successfully!");




//        // create token
//        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
//        String token = JWTUtils.createToken(userDetails.getUser().getId().intValue());
//
//        redisTemplate.opsForValue().set("Token:" + userDetails.getUser().getId(), JSON.toJSONString(userDetails.getUser()));
//
//        User user = this.getById(userDetails.getUser().getId().intValue());
//        TokenVo tokenVo = new TokenVo();
//        tokenVo.setUserName(user.getUserName());
//        tokenVo.setToken(token);


//        return tokenVo;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not exists by Username");
        }

//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), null);
        //return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }
}

