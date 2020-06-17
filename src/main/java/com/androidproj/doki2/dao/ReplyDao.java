package com.androidproj.doki2.dao;

import com.androidproj.doki2.entity.Reply;
import com.androidproj.doki2.entity.Saying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReplyDao extends JpaRepository<Reply,Integer> {
    Reply findByReplyId(int reply_id);
    //List<Reply> findAllBySaying_IdAndIsPublic(int saying_id,boolean isPublic);
    List<Reply> findAllBySaying_SayingIdAndIsPublic(int saying_id,boolean isPublic);
}
