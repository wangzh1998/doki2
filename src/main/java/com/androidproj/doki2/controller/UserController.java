package com.androidproj.doki2.controller;

import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.SayingService;
import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    //post登陆，登陆成功返回UserId,失败返回提示
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(name = "phoneNum")String phoneNum,
                      @RequestParam(name = "password")String password){
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return  userService.getUser(userDetails.getUsername());
        User user = userService.getUser(phoneNum);
        if(user==null)return "登陆失败：手机号不存在";
        else if(user.getPassword().equals(password))return "登陆成功：userId="+user.getUniqUserId();
        else return "登陆失败：手机号与密码不匹配";
    }

    //post注册,注册成功返回UserId,失败返回提示
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam(name = "phoneNum")String phoneNum,
                           @RequestParam(name = "realName")String realName,
                           @RequestParam(name = "password")String password) throws Exception {
        User user = new User();
        user.setUniqUserId(-1);
        user.setPhoneNumber(phoneNum);
        user.setRealName(realName);
        user.setPassword(password);
        try{
            this.userService.addUser(user);
            user = this.userService.getUser(phoneNum);
            return "注册成功："+user.getUniqUserId();
        }catch (Exception e){
            return "注册失败：当前手机号已存在";
        }
    }

    @RequestMapping("/query/myinfo")
    public User getUser(@RequestParam(name = "userId")int userId){
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return  userService.getUser(userDetails.getUsername());
        User user = userService.getUser(userId);
        user.setPassword(null);
        return user;
    }



    //post请求修改个人信息
    @RequestMapping(value = "/update/myinfo",method = RequestMethod.POST)
    public String updateUserInfo(@RequestParam(name = "userId")int userId,
                                 @RequestParam(name = "nickName")String nickName,
                                 @RequestParam(name = "realName")String realName,
                                 @RequestParam(name = "gender")int gender,
                                 @RequestParam(name = "email")String email,
                                 @RequestParam(name = "userImageSrc")String userImageSrc){
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(userId);
        try{
//            User user = this.userService.getUser(userDetails.getUsername());//getUsername获取的实际是手机号
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
    public String updateUserInfo(@RequestParam(name = "userId")int userId,
                                @RequestParam(name = "password")String password){
        User user = this.userService.getUser(userId);
        try{
            user.setPassword(password);
            this.userService.addUser(user);
            return "更新成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }


    //获取我发出的表白
    @RequestMapping(value = "/query/saying/from")
    List<Saying>  getMySendedSayings(@RequestParam(name = "userId")int userId) throws Exception{
        User user = this.userService.getUser(userId);
        if (user==null)throw new Exception("当前用户不存在");
        return this.sayingService.getRelatedPrivateFromSayings(userId);
    }
    //获取我收到的表白
    @RequestMapping(value = "/query/saying/to")
    List<Saying>  getMyGettedSayings(@RequestParam(name = "userId")int userId) throws Exception{
        User user = this.userService.getUser(userId);
        if (user==null)throw new Exception("当前用户不存在");
        return this.sayingService.getRelatedPrivateToSayings(userId);
    }
}
