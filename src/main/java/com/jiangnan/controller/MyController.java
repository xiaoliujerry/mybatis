package com.jiangnan.controller;

import com.jiangnan.common.Response;
import com.jiangnan.domain.User;
import com.jiangnan.service.MyService;
import com.jiangnan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyService myService;

    @GetMapping("/hello")
    public Response<String> test() {
        return Response.of("hello");
    }

    @GetMapping("/getUser")
    public Response<User> getUser(@RequestParam int id) {
//        return Response.of(userService.getById(id));
        return Response.of(myService.getUser(id));
    }
}
