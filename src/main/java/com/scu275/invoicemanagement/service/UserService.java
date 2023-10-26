package com.scu275.invoicemanagement.service;


import com.scu275.invoicemanagement.entity.User;
import com.scu275.invoicemanagement.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User signUpUser(User user) {
        // 校验用户是否已存在等逻辑
        // ...

        return userRepository.save(user);
    }
}

