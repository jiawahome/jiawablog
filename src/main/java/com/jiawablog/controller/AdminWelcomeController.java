package com.jiawablog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminWelcomeController {

    @GetMapping("/admin")
    private String admin() {
        return "redirect:/admin/welcome";
    }

    @GetMapping("/admin/welcome")
    private String welcome() {
        return "admin/welcome";
    }
}
