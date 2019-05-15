package com.jiawablog.mapper;

import com.jiawablog.db.User;
import org.apache.ibatis.annotations.Param;

public interface MineUserMapper {

    public User selectByLoginName(@Param(value="loginName") String loginName);
}
