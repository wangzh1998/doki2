package com.androidproj.doki2.controller;

import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.SayingService;
import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class SayingController {
    static int saying_num = 1;

    @Autowired
    SayingService sayingService;

    @Autowired
    UserService userService;

    @RequestMapping("/sayings")
    public List<Saying> getAllPublicSayings(){
        return sayingService.getAllPublicSayings();
    }

    @RequestMapping("/createSaying")
    public String createUsers() throws Exception {
        Saying saying = new Saying();
        User from_user = userService.getUser(2);
        saying.setFromUserPhoneNum(from_user.getPhoneNumber());
        saying.setFromUser(from_user);
        saying.setTime(new Date());
        saying.setContents("这是一条公开的表白说说。"+saying_num);
        saying_num++;
        saying.setPublic(true);
        saying.setSayingImageSrc("说说图片。");
        try{
            this.sayingService.addSaying(saying);
            return "添加成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
