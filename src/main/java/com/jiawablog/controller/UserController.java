package com.jiawablog.controller;

import com.jiawablog.db.User;
import com.jiawablog.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    public UserService userService;

    @RequestMapping(name = "/user/list", method = RequestMethod.GET)
    public List<User> list() {
        return userService.list();
    }
}
