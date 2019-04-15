package com.jiawablog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPageController {

    @GetMapping("/test-page")
    private String test() {
        return "test";
    }
}
