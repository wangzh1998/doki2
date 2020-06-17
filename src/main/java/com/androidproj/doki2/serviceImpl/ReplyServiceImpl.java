package com.androidproj.doki2.serviceImpl;

import com.androidproj.doki2.dao.ReplyDao;
import com.androidproj.doki2.dao.SecondReplyDao;
import com.androidproj.doki2.entity.Reply;
import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.SecondReply;
import com.androidproj.doki2.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyDao replyDao;
    @Autowired
    SecondReplyDao secondReplyDao;

    @Override
    public void addReply(Reply reply) throws Exception {
        Reply result = this.replyDao.save(reply);
        if(result==null)
            throw new Exception("保存评论失败");
    }

    @Override
    public void addSecondReply(SecondReply secondReply) throws Exception {
        SecondReply result = this.secondReplyDao.save(secondReply);
        if(result==null)
            throw new Exception("保存二级评论失败");
    }

    @Override
    public Reply getReply(int reply_id) {
            Reply result = this.replyDao.findByReplyId(reply_id);
            return result;
    }


    @Override
    public List<Reply> getAllPublicReply(int saying_id) {
        return this.replyDao.findAllBySaying_SayingIdAndIsPublic(saying_id,true);
    }

    @Override
    public List<Reply> getAllPrivateReply(int saying_id) {
        return this.replyDao.findAllBySaying_SayingIdAndIsPublic(saying_id,false);
    }
}
