package com.scu275.invoicemanagement.controller;

import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.dto.LoginDto;
import com.scu275.invoicemanagement.dto.SignUpDto;
import com.scu275.invoicemanagement.entity.User;
import com.scu275.invoicemanagement.entity.UserRepository;
import com.scu275.invoicemanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/signup")
    @Operation(summary = "signup", description = "signup user")
    public Result<String> register(@RequestBody SignUpDto signUpDto) {
        return userService.signUpUser(signUpDto);
    }

    @PostMapping("/login")
    public Result<String> authenticateUser(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}
