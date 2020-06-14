package com.androidproj.doki2.service;

import com.androidproj.doki2.entity.Reply;
import com.androidproj.doki2.entity.Saying;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReplyService {
    Reply addReply(Reply reply) throws Exception;
    Reply getReply(int reply_id) throws Exception;
    List<Reply> getAllPublicReply(Saying saying);
    List<Reply> getAllPrivateReply(Saying saying);
}
