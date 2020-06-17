package com.androidproj.doki2.service;

import com.androidproj.doki2.entity.Reply;
import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.SecondReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReplyService {
    void addReply(Reply reply) throws Exception;
    void addSecondReply(SecondReply secondReply)throws Exception;
    Reply getReply(int reply_id);
    List<Reply> getAllPublicReply(int saying_id);
    List<Reply> getAllPrivateReply(int saying_id);
}
