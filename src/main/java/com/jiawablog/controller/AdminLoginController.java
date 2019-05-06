package com.jiawablog.controller;

import com.jiawablog.dto.UserDto;
import com.jiawablog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @ResponseBody
    public Object doLogin(UserDto userDto, HttpServletRequest request) {
        Integer i = userService.doLogin(userDto);
        if (i == 1) {
            request.getSession().setAttribute("user", userDto);
        }
        return i;
    }
}
