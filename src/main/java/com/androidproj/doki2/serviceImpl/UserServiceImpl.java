package com.androidproj.doki2.serviceImpl;

import com.androidproj.doki2.dao.UserDao;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {

    }

    @Autowired
    UserDao userDao;


    @Override
    public List<User> getAllUsers() {
        return this.userDao.findAll();
    }

    @Override
    public User addUser(User user) throws Exception {
        User result = this.userDao.save(user);
        if (result==null)
            throw new Exception("添加用户失败");
        return result;
    }

    @Override
    public User getUser(int user_id) throws Exception {
        User result = this.userDao.findUserByUniqUserId(user_id);
        if (result==null)
            throw new Exception("查找用户失败");
        return result;
    }


}
