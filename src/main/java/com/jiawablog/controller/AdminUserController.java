package com.jiawablog.controller;

import com.jiawablog.dto.UserDto;
import com.jiawablog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/admin/user/save")
    @ResponseBody
    private String save(UserDto userDto) {
        int i = userService.save(userDto);
        if (i == -1) {
            return "exist";
        }
        return "success";
    }

    /**
     * restful风格
     * xxx/delete?id=xxx
     * xxx/delete/xxx
     * @param id
     * @return
     */
    @DeleteMapping("/admin/user/delete/{id}")
    @ResponseBody
    private String delete(@PathVariable String id) {
        userService.delete(id);
        return "success";
    }
}
