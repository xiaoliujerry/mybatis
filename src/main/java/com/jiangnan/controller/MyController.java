package com.jiangnan.controller;

import com.jiangnan.common.Response;
import com.jiangnan.common.UserInfoHolder;
import com.jiangnan.domain.User;
import com.jiangnan.service.MyService;
import com.jiangnan.service.TestService;
import com.jiangnan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@Validated
public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyService myService;

    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public Response<String> test() {
        testService.test();
        return Response.of("hello");
    }

    @GetMapping("/getUser")
    public Response<User> getUser(@Min(value = 0, message = "min value is 10") @RequestParam int id) {
//        return Response.of(userService.getById(id));
        System.out.println(UserInfoHolder.getLoginInfo().getToken());
        return Response.of(myService.getUser(id));
    }
}
