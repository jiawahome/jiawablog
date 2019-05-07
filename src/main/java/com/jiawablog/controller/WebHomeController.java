package com.jiawablog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebHomeController {

    @GetMapping("/")
    public String login() {
        return "web/index";
    }

}
