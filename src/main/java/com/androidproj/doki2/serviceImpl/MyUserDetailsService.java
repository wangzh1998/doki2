package com.androidproj.doki2.serviceImpl;

import com.androidproj.doki2.dao.UserDao;
import com.androidproj.doki2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    //当前方法重写为根据手机号来加载用户信息
    @Override
    public UserDetails loadUserByUsername(String phoneNum) throws UsernameNotFoundException {
        User user = this.userDao.findUserByPhoneNumber(phoneNum);
        if (user == null) {
            throw new UsernameNotFoundException("当前手机号码不存在");
        } else {
            return new org.springframework.security.core.userdetails.User(phoneNum,user.getPassword(),null);
        }
    }
}
