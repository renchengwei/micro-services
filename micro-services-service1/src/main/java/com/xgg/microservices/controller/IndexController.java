package com.xgg.microservices.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
@RestController
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "hello";
    }
}
