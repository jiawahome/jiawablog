package com.jiawablog.service;

import com.jiawablog.db.User;
import com.jiawablog.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    public UserMapper userMapper;

    public List<User> list() {
        return userMapper.list();
    }

    public int create(User user) {
        return userMapper.create(user);
    }
}
