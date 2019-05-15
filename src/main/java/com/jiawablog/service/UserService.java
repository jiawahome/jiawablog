package com.jiawablog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawablog.db.User;
import com.jiawablog.db.UserExample;
import com.jiawablog.dto.PageDto;
import com.jiawablog.dto.UserDto;
import com.jiawablog.mapper.MineUserMapper;
import com.jiawablog.mapper.UserMapper;
import com.jiawablog.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    public UserMapper userMapper;

    @Resource
    public MineUserMapper mineUserMapper;

    public List<UserDto> list(PageDto pageDto) {
        // 只对第一个查询语句有效
        PageHelper.startPage(pageDto.getCurrent(), pageDto.getSize());
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userMapper.selectByExample(null);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        long total = pageInfo.getTotal(); // 得到总行数
        pageDto.setTotal(total);

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

    /**
     * 保存用户
     * 当返回-1时，表示用户名已存在
     * @param userDto
     * @return
     */
    public int save(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLoginName(userDto.getLoginName());
        user.setPassword(userDto.getPassword());
        int i = userMapper.updateByPrimaryKey(user);
        if (i == 0) {
            // 校验用户名是否存在
            User userDb = this.selectByLoginName(user.getLoginName());
            if (userDb != null) {
                return -1;
            } else {
                // 用户名不存在
                //  String id = UUID.randomUUID().toString().replace("-", "");
                String id = UuidUtil.uuid();
                user.setId(id);
                i = this.create(user);
            }
        }
        return i;
    }

    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 登录功能
     * 1：登录成功
     * -1： 登录失败
     * @param userDto
     * @return
     */
    public int doLogin(UserDto userDto) {
//        User user = this.selectByLoginName(userDto.getLoginName());
        User user = mineUserMapper.selectByLoginName(userDto.getLoginName());
        if (user == null) {
            LOG.info("用户名不对");
            return -1;
        } else {
            if (user.getPassword().equals(userDto.getPassword())) {
                LOG.info("登录成功");
                return 1;
            } else {
                LOG.info("密码错");
                return -1;
            }
        }
    }
}
