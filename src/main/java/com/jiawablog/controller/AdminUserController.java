package com.jiawablog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminUserController {

    @GetMapping("/admin/user")
    private String user() {
        return "admin/user/user";
    }

    @GetMapping("/admin/user/list")
    private String list() {
        return "admin/user/list";
    }
}
