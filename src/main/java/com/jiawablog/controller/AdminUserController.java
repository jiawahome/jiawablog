package com.jiawablog.controller;

import com.jiawablog.db.User;
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
        List<User> userList = userService.list();
        model.addAttribute("list", userList);
        return "admin/user/list";
    }

    @PostMapping("/admin/user/update")
    @ResponseBody
    private String update(User user) {
        userService.update(user);
        return "success";
    }
}
