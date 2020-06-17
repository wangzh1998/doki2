package com.androidproj.doki2.controller;

import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.SayingService;
import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/square")
public class SquareController {
    @Autowired
    UserService userService;
    @Autowired
    SayingService sayingService;

    @RequestMapping("/query/sayings")
    //做分页查询 需要在contex中维护用户当前查询到的纪录 或者 标页码
    List<Saying> getPublicSayings(@RequestParam(name = "pageNum",required = false,defaultValue = "0")int pageNum){
        System.out.println("pageNum==" + pageNum);
        Page<Saying> res = sayingService.getPublicSayings(pageNum);
//        System.out.println("res.getTotalElements()"+res.getTotalElements());
//        System.out.println("res.getContent()"+res.getContent());
//        System.out.println("res.getTotalPages()"+res.getTotalPages());
//        System.out.println(res);
        return res.getContent();
    }

    @RequestMapping("/add/saying")
    public String addSaying(@RequestParam(name = "userId")int userId,
                            @RequestParam(name = "sayingContent")String sayingContent,
                            @RequestParam(name = "toUserPhoneNum")String toUserPhoneNum,
                            @RequestParam(name = "sayingImageSrc")String sayingImageSrc,
                            @RequestParam(name = "isPublic")boolean isPublic) {
        System.out.println(userId+sayingContent+toUserPhoneNum+sayingImageSrc+isPublic);
        User from_user =this.userService.getUser(userId);
        if(from_user==null)return "用户不存在";
        Saying saying = new Saying();
        saying.setSayingId(-1);
        saying.setFromUserPhoneNum(from_user.getPhoneNumber());
        saying.setFromUser(from_user);
        saying.setToUserPhoneNum(toUserPhoneNum);
        saying.setContents(sayingContent);
        saying.setPublic(isPublic);
        saying.setSayingImageSrc(sayingImageSrc);
        System.out.println(saying);
        try{
            this.sayingService.addSaying(saying);
            return "添加成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
