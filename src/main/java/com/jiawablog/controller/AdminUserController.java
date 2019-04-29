package com.jiawablog.controller;

import com.jiawablog.dto.PageDto;
import com.jiawablog.dto.UserDto;
import com.jiawablog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminUserController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminUserController.class);

    @Resource
    private UserService userService;

    @GetMapping("/admin/user")
    private String user() {
        return "admin/user/user";
    }

    @GetMapping("/admin/user/list")
    private String list(Model model, PageDto pageDto) {
        LOG.info("用户列表查询开始：{}", pageDto.toString());
        List<UserDto> userList = userService.list();
        model.addAttribute("list", userList);

        pageDto.setCount(10);
        model.addAttribute("page", pageDto);
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
