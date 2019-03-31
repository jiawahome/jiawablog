package com.jiawablog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/test")
    public String test() {
        return "TEST!";
    }

    @RequestMapping("/hello2")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}