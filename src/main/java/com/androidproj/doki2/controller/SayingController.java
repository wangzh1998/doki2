package com.androidproj.doki2.controller;

import com.androidproj.doki2.entity.Reply;
import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.SecondReply;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.ReplyService;
import com.androidproj.doki2.service.SayingService;
import com.androidproj.doki2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/saying")

public class SayingController {
    @Autowired
    UserService userService;

    @Autowired
    ReplyService replyService;

    @Autowired
    SayingService sayingService;


    @RequestMapping("/query/replies")
    List<Reply> getReplies(@RequestParam(name = "sayingId")int sayingId,@RequestParam(name = "isPublic")boolean isPublic){
        if(isPublic){
            return this.replyService.getAllPublicReply(sayingId);
        }else{
            //从环境中查询用用户信息，如果用户信息和当前saing对得上，才执行查询私有评论的操作
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userDetails.getUsername()!=null){
                Saying saying = this.sayingService.getSaying(sayingId);
                if(userDetails.getUsername().equals(saying.getFromUserPhoneNum())
                        ||userDetails.getUsername().equals(saying.getToUserPhoneNum()))
                return this.replyService.getAllPrivateReply(sayingId);
            }
        }
        return null;
    }
    @RequestMapping("/add/reply/one")
    String addReply(@RequestParam(name = "sayingId")int sayingId,
                         @RequestParam(name = "replyContent")String replyContent,
                         @RequestParam(name = "isPublic")boolean isPublic){
        Reply reply = new Reply();
        Saying saying = this.sayingService.getSaying(sayingId);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(userDetails.getUsername());
        //TODO 这里一定要设置ID!!!!随便设！！不然就会报错！！！！不知道为什么！！！
        reply.setReplyId(-1);
        reply.setSaying(saying);
        reply.setUser(user);
        reply.setReplyContent(replyContent);
        reply.setPublic(isPublic);
        if(isPublic){
            try{
                this.replyService.addReply(reply);
                return "添加公有一级评论成功";
            }catch (Exception e){
                return e.getMessage();
            }
        }else{
            //从环境中查询用用户信息，如果用户信息和当前saing对得上，才执行添加私有评论的操作
            if(userDetails.getUsername()!=null){
                if(userDetails.getUsername().equals(saying.getFromUserPhoneNum())
                        ||userDetails.getUsername().equals(saying.getToUserPhoneNum()))
                    try{
                        this.replyService.addReply(reply);
                        return "添加私有一级评论成功";
                    }catch (Exception e){
                        return e.getMessage();
                    }
            }
        }
        return null;
    }
    @RequestMapping("/add/reply/two")
    List<Reply> addReply(@RequestParam(name = "father_reply_id")int father_reply_id,//一级评论id
                         @RequestParam(name = "reply_user")int reply_user_id,//被回复的二级评论用户
                         @RequestParam(name = "reply_content")String reply_contenet,
                         @RequestParam(name = "isPublic")boolean isPublic){
        //TODO
//        SecondReply secondReply = new SecondReply();
//        Reply fatherReply = this.replyService.getReply(father_reply_id);
//        User reply_user = this.userService.getUser(reply_user_id);
//        User user = ;//从环境中获取用户信息
//        //TODO 这里一定要设置ID!!!!随便设！！不然就会报错！！！！不知道为什么！！！
//        secondReply.setSecondReplyId(-1);
//        secondReply.setFatherReply();
//        secondReply.setUser(user);
//        secondReply.setReplyContent(reply_contenet);
//        secondReply.setPublic(isPublic);
//        if(isPublic){
//            return this.replyService.addSecondReply();
//        }else{
//            //TODO 从环境中查询用用户信息，如果用户信息和当前saing对得上，才执行添加私有评论的操作
//            return this.replyService.addSecondReply();
//        }
        return null;
    }

    @RequestMapping("/connect/toUser") //如果当前用户的手机号码和saying中的接收方号码相同，将当前用户关联为当前saying和接收方
    public String connectSayingToUser(@RequestParam(name = "sayingId")int sayingId) throws Exception {
        Saying saying = this.sayingService.getSaying(sayingId);
        //User user = //从Contex中获取
        //String toUserPhoneNum = //从Contex中获取

        //TODO
//        try{
//            if(toUserPhoneNum .equals(user.getToUserPhoneNum())){
//                saying.setToUser(user);
//                this.sayingService.addSaying(saying);
//            }
//            return "关联成功";
//        }catch (Exception e){
//            return e.getMessage();
//        }
        return null;
    }




}
