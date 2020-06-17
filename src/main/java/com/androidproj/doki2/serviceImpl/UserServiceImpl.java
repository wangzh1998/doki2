package com.androidproj.doki2.serviceImpl;

import com.androidproj.doki2.dao.UserDao;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public void addUser(User user) throws Exception {
        User result = this.userDao.save(user);
        if (result==null)
            throw new Exception("添加用户失败");
    }

    @Override
    public User getUser(int user_id){
        User result = this.userDao.findUserByUniqUserId(user_id);
        return result;
    }
    @Override
    public User getUser(String phoneNum) {
        User result = this.userDao.findUserByPhoneNumber(phoneNum);
        return result;
    }
}
