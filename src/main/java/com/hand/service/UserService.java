package com.hand.service;

import com.hand.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
public interface UserService {
    User getUserById(Integer id);
    List<User> listUser();
    void save(User user);
    void remove(int id);
    void updateUser(User user);
    User checkLogin(String userName);
}
