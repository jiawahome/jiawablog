package com.jiawablog.controller;

import com.jiawablog.dto.UserDto;
import com.jiawablog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class AdminLoginController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminUserController.class);

    @Resource
    private UserService userService;

    @GetMapping("/admin/login")
    public String login() {
        return "admin/login/login";
    }

    @PostMapping("/admin/doLogin")
    public int doLogin(UserDto userDto) {
        int i = userService.doLogin(userDto);
        return i;
    }
}
