package com.jiawablog.controller;

import com.jiawablog.dto.UserDto;
import com.jiawablog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminUserController {

    @Resource
    private UserService userService;

    @GetMapping("/admin/user")
    private String user() {
        return "admin/user/user";
    }

    @GetMapping("/admin/user/list")
    private String list(Model model) {
        List<UserDto> userList = userService.list();
        model.addAttribute("list", userList);
        return "admin/user/list";
    }

    @PostMapping("/admin/user/update")
    @ResponseBody
    private String update(UserDto userDto) {
        userService.update(userDto);
        return "success";
    }
}
