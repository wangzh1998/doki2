package com.androidproj.doki2.controller;

import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.SayingService;
import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    SayingService sayingService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping("/query/myinfo")
    public User getUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  userService.getUser(userDetails.getUsername());

    }

    //注册是POST方法
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam(name = "phoneNum")String phoneNum,
                           @RequestParam(name = "realName")String realName,
                           @RequestParam(name = "password")String password) throws Exception {
        System.out.println(phoneNum+realName+password+"11111111");
        User user = new User();
        user.setUniqUserId(-1);
        user.setPhoneNumber(phoneNum);
        user.setRealName(realName);
        System.out.println(phoneNum+realName+password+"222222222");
        user.setPassword(bCryptPasswordEncoder.encode(password));
        System.out.println(phoneNum+realName+password+"3333333333");
        try{
             this.userService.addUser(user);
             //测试 //TODO 删除
            System.out.println("55555555555555555");
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(userDetails+"6666666666666");
            System.out.println(userDetails.getUsername()+userDetails.getPassword()+userDetails.getAuthorities()+"4444444444");
             return "注册成功";
        }catch (Exception e){
            //TODO 补充手机号已经存在的情况的返回说明
            return e.getMessage();
        }
    }

    @RequestMapping("/update/myinfo")
    public String updateUserInfo(@RequestParam(name = "nickName")String nickName,
                                 @RequestParam(name = "realName")String realName,
                                 @RequestParam(name = "gender")int gender,
                                 @RequestParam(name = "email")String email,
                                 @RequestParam(name = "userImageSrc")String userImageSrc){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            User user = this.userService.getUser(userDetails.getUsername());//getUsername获取的实际是手机号
            user.setNickName(nickName);
            user.setRealName(realName);
            user.setGender(gender);
            user.setEmail(email);
            user.setUserImageSrc(userImageSrc);
            this.userService.addUser(user);//更新
            return "更新成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    //修改密码是用post请求
    @RequestMapping(value = "/update/password",method = RequestMethod.POST)
    public String updateUserInfo(@RequestParam(name = "password")String password){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            User user = this.userService.getUser(userDetails.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(password));
            this.userService.addUser(user);
            return "更新成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
