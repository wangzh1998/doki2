package com.androidproj.doki2.controller;

import com.androidproj.doki2.entity.Reply;
import com.androidproj.doki2.entity.Saying;
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
    @Autowired
    ReplyService replyService;

    @Autowired
    SayingService sayingService;


    @RequestMapping("/replysPublic")
    public List<Reply> getAllPublicReplys(@RequestParam(name = "saying_id")int saying_id){
        try {
            Saying saying = sayingService.getSaying(saying_id);
            return replyService.getAllPublicReply(saying);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping("/replysPrivate")
    public List<Reply> getAllPrivateReplys(@RequestParam(name = "saying_id")int saying_id){
        try {
            Saying saying = sayingService.getSaying(saying_id);
            return replyService.getAllPrivateReply(saying);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping("/createPublicOneLevel")
    public String PublicOneLevel(@RequestParam(name = "saying_id")int saying_id) throws Exception {
        Saying saying = sayingService.getSaying(saying_id);
        Reply oneLevel = new Reply();
        oneLevel.setSaying(saying);
        oneLevel.setFatherReply(null);
        oneLevel.setPublic(true);
        oneLevel.setReplyContent(saying_id+"号说说的一级公开评论");
        try{
            this.replyService.addReply(oneLevel);
            return "添加成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping("/createPublicTwoLevel")
    public String createPublicTwoLevel(@RequestParam(name = "reply_id")int reply_id) throws Exception {
        Reply fatherReply = replyService.getReply(reply_id);
        Reply twoLevel = new Reply();
        twoLevel.setSaying(null);
        twoLevel.setFatherReply(fatherReply);
        twoLevel.setLevel(2);
        twoLevel.setPublic(true);
        twoLevel.setReplyContent(fatherReply.getSaying().getSayingId()+"号说说下的"+fatherReply.getReplyId()+"一级评论的二级评论");
        try{
            this.replyService.addReply(twoLevel);
            return "添加成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping("/createPrivateOneLevel")
    public String createPrivateOneLevel(@RequestParam(name = "saying_id")int saying_id) throws Exception {
        Saying saying = sayingService.getSaying(saying_id);
        Reply oneLevel = new Reply();
        oneLevel.setSaying(saying);
        oneLevel.setFatherReply(null);
        oneLevel.setPublic(false);
        oneLevel.setReplyContent(saying_id+"号说说的一级私有评论");
        try{
            this.replyService.addReply(oneLevel);
            return "添加成功";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
