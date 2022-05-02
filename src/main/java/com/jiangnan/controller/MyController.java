package com.jiangnan.controller;

import com.jiangnan.domain.User;
import com.jiangnan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String test() {
        return "hello";
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam int id) {
        return userService.getById(id);
    }
}
