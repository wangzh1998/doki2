//package com.androidproj.doki2.controller;
//
//import com.androidproj.doki2.entity.Saying;
//import com.androidproj.doki2.entity.User;
//import com.androidproj.doki2.service.SayingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@RequestMapping("/square")
//public class SquareController {
//    @Autowired
//    SayingService sayingService;
//
//    @RequestMapping("/query/sayings")
//    //TODO 做分页查询 需要在contex中维护用户当前查询到的纪录 或者 标页码
//    List<Saying> getAllPublicSayings(){
//        return this.sayingService.getAllPublicSayings();
//    }
//
//    @RequestMapping("/add/saying")
//    public String addSaying(@RequestParam(name = "sayingContent")String sayingContent,
//                            @RequestParam(name = "toUserPhoneNum")String toUserPhoneNum,
//                            @RequestParam(name = "sayingImageSrc")String sayingImageSrc,
//                            @RequestParam(name = "isPublic")boolean isPublic) throws Exception {
//        Saying saying = new Saying();
//        //User from_user = //从Contex中获取
//        //String from_user_phone_num = //从Contex中获取
//        saying.setFromUserPhoneNum(from_user.getPhoneNumber());
//        saying.setFromUser(from_user);
//        saying.setToUserPhoneNum(toUserPhoneNum);
//        saying.setContents(sayingContent);
//        saying.setPublic(isPublic);
//        saying.setSayingImageSrc(sayingImageSrc);
//        System.out.println(saying);
//        try{
//            this.sayingService.addSaying(saying);
//            return "添加成功";
//        }catch (Exception e){
//            return e.getMessage();
//        }
//    }
//}
