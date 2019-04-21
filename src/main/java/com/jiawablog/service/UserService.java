package com.jiawablog.service;

import com.jiawablog.db.User;
import com.jiawablog.dto.UserDto;
import com.jiawablog.mapper.UserMapper;
import com.jiawablog.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Resource
    public UserMapper userMapper;

    public List<UserDto> list() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userMapper.selectByExample(null);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setLoginName(user.getLoginName());
            userDto.setPassword(user.getPassword());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public int create(User user) {
        return userMapper.insert(user);
    }

    public int delete(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int save(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLoginName(userDto.getLoginName());
        user.setPassword(userDto.getPassword());
        int i = userMapper.updateByPrimaryKey(user);
        if (i == 0) {
//            String id = UUID.randomUUID().toString().replace("-", "");
            String id = UuidUtil.uuid();
            user.setId(id);
            i = this.create(user);
        }
        return i;
    }

//    public List<User> list() {
//        return userMapper.list();
//    }
//
//    public int create(User user) {
//        return userMapper.create(user);
//    }
//
//    public int delete(String id) {
//        return userMapper.delete(id);
//    }
//
//    public int update(User user) {
//        return userMapper.update(user);
//    }
}
