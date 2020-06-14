package com.androidproj.doki2.controller;

import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    static int idx = 0;

    @Autowired
    UserService userService;

    @RequestMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping("/createUser")
    public String createUsers() throws Exception {
        User user = new User();
        user.setGender(0);
        user.setNickName("nick");
        user.setRealName("real");
        user.setPassword("1234");
        user.setPhoneNumber("1234567890"+idx);
        idx++;
        user.setUserImageSrc("image");
        try{
             this.userService.addUser(user);
             return "添加成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
