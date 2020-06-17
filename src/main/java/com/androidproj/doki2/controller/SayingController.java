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
    List<Reply> getReplies(@RequestParam(name = "userId")int userId,
                           @RequestParam(name = "sayingId")int sayingId,
                           @RequestParam(name = "isPublic")boolean isPublic){
        if(isPublic){
            return this.replyService.getAllPublicReply(sayingId);
        }else{
            //从查询用用户信息，如果用户信息和当前saing对得上，才执行查询私有评论的操作
            User user = userService.getUser(userId);
            if(user!=null){
                Saying saying = this.sayingService.getSaying(sayingId);
                if(saying!=null){
                    if(user.getPhoneNumber().equals(saying.getFromUserPhoneNum())
                            ||user.getPhoneNumber().equals(saying.getToUserPhoneNum()))
                        return this.replyService.getAllPrivateReply(sayingId);
                }
            }else{
                return null;
            }
        }
        return null;
    }
    @RequestMapping("/add/reply/one")
    String addOneReply(@RequestParam(name = "userId")int userId,
                    @RequestParam(name = "sayingId")int sayingId,
                         @RequestParam(name = "replyContent")String replyContent,
                         @RequestParam(name = "isPublic")boolean isPublic){
        Reply reply = new Reply();
        Saying saying = this.sayingService.getSaying(sayingId);
        if(saying==null)return "当前说说不存在";
        User user = this.userService.getUser(userId);
        if(user==null)return "当前用户不存在";
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
            if(user!=null){
                saying = this.sayingService.getSaying(sayingId);
                if(saying==null)return "当前说说不存在";
                if(user.getPhoneNumber().equals(saying.getFromUserPhoneNum())
                        ||user.getPhoneNumber().equals(saying.getToUserPhoneNum())){
                    try{
                        this.replyService.addReply(reply);
                        return "添加私有一级评论成功";
                    }catch (Exception e){
                        return e.getMessage();
                    }
                }
            }else{
                return null;
            }
        }
        return null;
    }
    @RequestMapping("/add/reply/two")
    String addSecondReply(@RequestParam(name = "userId")int userId,
                          @RequestParam(name = "sayingId")int sayingId,
                          @RequestParam(name = "fatherReplyId")int fatherReplyId,//一级评论id
                         @RequestParam(name = "replyUserId",required = false,defaultValue = "0")int replyUserId,//被回复的二级评论用户
                         @RequestParam(name = "replyContent")String replyContent,
                         @RequestParam(name = "isPublic")boolean isPublic){
        User user = this.userService.getUser(userId);
        if(user==null)return "当前用户不存在";
        SecondReply secondReply = new SecondReply();
        Reply fatherReply = this.replyService.getReply(fatherReplyId);
        User replyUser = this.userService.getUser(replyUserId);
        secondReply.setSecondReplyId(-1);
        secondReply.setUser(user);
        secondReply.setFatherReply(fatherReply);
        secondReply.setReplyUser(replyUser);
        secondReply.setReplyContent(replyContent);
        secondReply.setPublic(isPublic);
        if(isPublic){
            try{
                this.replyService.addSecondReply(secondReply);
                return "添加公有二级评论成功";
            }catch (Exception e){
                return e.getMessage();
            }
        }else{
            //从环境中查询用用户信息，如果用户信息和当前saing对得上，才执行添加私有评论的操作
            if(user!=null){
                Saying saying = this.sayingService.getSaying(sayingId);
                if(saying==null)return "当前说说不存在";
                if(user.getPhoneNumber().equals(saying.getFromUserPhoneNum())
                        ||user.getPhoneNumber().equals(saying.getToUserPhoneNum())){
                    try{
                        this.replyService.addSecondReply(secondReply);
                        return "添加私有二级评论成功";
                    }catch (Exception e){
                        return e.getMessage();
                    }
                }
            }else{
                return null;
            }
        }
        return null;
    }

    @RequestMapping("/connect/toUser") //如果当前用户的手机号码和saying中的接收方号码相同，将当前用户关联为当前saying和接收方
    public String connectSayingToUser(@RequestParam(name = "userId")int userId,
                                      @RequestParam(name = "sayingId")int sayingId) {
        User user = this.userService.getUser(userId);
        Saying saying = this.sayingService.getSaying(sayingId);
        if(user.getPhoneNumber().equals(saying.getToUserPhoneNum())){
            saying.setToUser(user);
            try{
                this.sayingService.addSaying(saying);
                return "当前用户成功与saying关联";
            }catch (Exception e){
                return e.getMessage();
            }
        }
        return "当前用户并非saying的接收方，无法关联";
    }




}
