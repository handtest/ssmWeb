package com.hand.service.impl;

import com.hand.entity.User;
import com.hand.dao.UserMapper;
import com.hand.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    public User getUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    public List<User> listUser() {
        List<User> listUser = userMapper.selectAll();
        return listUser;
    }

    public void save(User user) {
        userMapper.insert(user);
    }

    public void remove(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    public User checkLogin(String userName) {
        User user = userMapper.checkLogin(userName);
        return user;
    }
}
