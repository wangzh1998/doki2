package com.androidproj.doki2.service;

import com.androidproj.doki2.entity.User;

import java.util.List;

public interface UserService{
//    //根据用户id查询用户
//    User queryUser(int user_id) throws Exception;
//    //根据电话号码查询用户
//    User queryUser(String phone_num) throws Exception;

    List<User> getAllUsers();
    User addUser(User user) throws Exception;
    User getUser(int user_id) throws Exception;
}
