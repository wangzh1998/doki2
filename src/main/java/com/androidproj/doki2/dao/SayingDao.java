package com.androidproj.doki2.dao;

import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SayingDao extends JpaRepository<Saying,Integer> {
    //List<Saying> findAll();
    Page<Saying> findAllByIsPublic(boolean isPublic, Pageable pageable);
    //传参只能一个个列出来，不能传对象
    List<Saying> findAllByFromUser_UniqUserId(int fromUserId);
    List<Saying> findAllByToUser_UniqUserId(int toUserId);
    Saying findBySayingId(int saying_id);

    //Page<Saying> findAllByIsPublic(boolean );
}
