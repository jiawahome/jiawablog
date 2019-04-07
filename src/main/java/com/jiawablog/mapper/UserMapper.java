package com.jiawablog.mapper;

import com.jiawablog.db.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    public List<User> list();

    public int create(User user);

    public int delete(@Param("id") String userId);
}
