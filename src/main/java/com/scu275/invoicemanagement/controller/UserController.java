package com.scu275.invoicemanagement.controller;

import com.scu275.invoicemanagement.entity.User;
import com.scu275.invoicemanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    @Operation(summary = "signup", description = "signup user")
    public ResponseEntity<String> register(@RequestBody User user) {
        User registeredUser = userService.signUpUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
