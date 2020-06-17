package com.androidproj.doki2.controller;

import com.androidproj.doki2.entity.Reply;
import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.SecondReply;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.ReplyService;
import com.androidproj.doki2.service.SayingService;
import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ReplyController {
//    @Autowired
//    ReplyService replyService;
//
//    @Autowired
//    SayingService sayingService;
//
//    @Autowired
//    UserService userService;
//
//
//    @RequestMapping("/replysPublic")
//    public List<Reply> getAllPublicReplys(@RequestParam(name = "saying_id")int saying_id){
//        try {
//            System.out.println(saying_id);
//            //Saying saying = sayingService.getSaying(saying_id);
//            //System.out.println(saying);
//            return replyService.getAllPublicReply(saying_id);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//
//    @RequestMapping("/replysPrivate")
//    public List<Reply> getAllPrivateReplys(@RequestParam(name = "saying_id")int saying_id){
//        try {
//            //Saying saying = sayingService.getSaying(saying_id);
//            return replyService.getAllPrivateReply(saying_id);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
//
//    @RequestMapping("/createPublicOneLevel")
//    public String PublicOneLevel(@RequestParam(name = "saying_id")int saying_id,
//                                 @RequestParam(name = "user_id")int user_id) throws Exception {
//        Saying saying = sayingService.getSaying(saying_id);
//        User user = userService.getUser(user_id);
//        Reply oneLevel = new Reply();
//        //TODO 这里一定要设置ID!!!!随便设！！不然就会报错！！！！不知道为什么！！！
//        oneLevel.setReplyId(-1);
//        oneLevel.setSaying(saying);
//        oneLevel.setUser(user);
//        oneLevel.setPublic(true);
//        oneLevel.setReplyContent(saying_id+"号说说的一级公开评论");
//        System.out.println(oneLevel.toString());
//        try{
//            this.replyService.addReply(oneLevel);
//            return "添加成功";
//        }catch (Exception e){
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping("/createPublicTwoLevel")
//    public String createPublicTwoLevel(@RequestParam(name = "reply_id")int reply_id,
//                                       @RequestParam(name = "reply_user_id")int reply_user_id) throws Exception {
//        Reply fatherReply = replyService.getReply(reply_id);
//        SecondReply twoLevel = new SecondReply();
//        twoLevel.setSecondReplyId(-1);
//        twoLevel.setPublic(true);
//        twoLevel.setFatherReply(fatherReply);
//        twoLevel.setPublic(true);
//        User replyUser = userService.getUser(reply_user_id);
//        twoLevel.setReplyUser(replyUser);
//        twoLevel.setReplyContent(fatherReply.getReplyId()+"号一级评论的二级评论");
//        try{
//            this.replyService.addSecondReply(twoLevel);
//            return "添加成功";
//        }catch (Exception e){
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping("/createPrivateOneLevel")
//    public String createPrivateOneLevel(@RequestParam(name = "saying_id")int saying_id) throws Exception {
//        Saying saying = sayingService.getSaying(saying_id);
//        Reply oneLevel = new Reply();
//        oneLevel.setReplyId(-1);
//        oneLevel.setSaying(saying);
//        oneLevel.setPublic(false);
//        oneLevel.setReplyContent(saying_id+"号说说的一级私有评论");
//        try{
//            this.replyService.addReply(oneLevel);
//            return "添加成功";
//        }catch (Exception e){
//            return e.getMessage();
//        }
//    }
}
