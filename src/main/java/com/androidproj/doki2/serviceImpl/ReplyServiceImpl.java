package com.androidproj.doki2.serviceImpl;

import com.androidproj.doki2.dao.ReplyDao;
import com.androidproj.doki2.entity.Reply;
import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyDao replyDao;

    @Override
    public Reply addReply(Reply reply) throws Exception {
        Reply result = this.replyDao.save(reply);
        if(result==null)
            throw new Exception("保存评论失败");
        return result;
    }

    @Override
    public Reply getReply(int reply_id) throws Exception {
            Reply result = this.replyDao.findByReplyId(reply_id);
            if(result == null)
                throw new Exception("查找评论失败");
            return result;
    }


    @Override
    public List<Reply> getAllPublicReply(Saying saying) {
        return this.replyDao.findAllBySayingAndIsPublic(saying,true);
    }

    @Override
    public List<Reply> getAllPrivateReply(Saying saying) {
        return this.replyDao.findAllBySayingAndIsPublic(saying,false);
    }
}
