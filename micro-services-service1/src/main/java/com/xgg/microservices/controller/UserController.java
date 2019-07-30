package com.xgg.microservices.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
/**
 *  @author: renchengwei
 *  @Date: 2019-07-30
 *  @Description:
 */
public class UserController {

    @RequestMapping("/login")

    public String login() {
        return "login success";
    }
}
