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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/square")
public class SquareController {
    @Autowired
    UserService userService;
    @Autowired
    SayingService sayingService;

    @RequestMapping("/query/sayings")
    //TODO 做分页查询 需要在contex中维护用户当前查询到的纪录 或者 标页码
    Page<Saying> getPublicSayings(@RequestParam(name = "pageNum",required = false,defaultValue = "0")int pageNum){
        System.out.println("pageNum==" + pageNum);
        Page<Saying> res = sayingService.getPublicSayings(pageNum);
        System.out.println("res.getTotalElements()"+res.getTotalElements());
        System.out.println("res.getContent()"+res.getContent());
        System.out.println("res.getTotalPages()"+res.getTotalPages());
        System.out.println(res);
        return res;
    }

    @RequestMapping("/add/saying")
    public String addSaying(@RequestParam(name = "sayingContent")String sayingContent,
                            @RequestParam(name = "toUserPhoneNum")String toUserPhoneNum,
                            @RequestParam(name = "sayingImageSrc")String sayingImageSrc,
                            @RequestParam(name = "isPublic")boolean isPublic) throws Exception {
        Saying saying = new Saying();
        //TODO
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User from_user =this.userService.getUser(userDetails.getUsername());
        User from_user =this.userService.getUser(1);
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
