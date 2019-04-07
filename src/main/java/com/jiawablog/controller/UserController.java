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

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> list() {
        return userService.list();
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public int create(User user) {
        /*
        User user = new User();
        user.setId("3333");
        user.setLoginName("test3");
        user.setPassword("test3");
        */
        return userService.create(user);
    }

//    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
//    public int delete(User user) {
//        return userService.delete(user);
//    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public int delete(String id) {
        return userService.delete(id);
    }
}
