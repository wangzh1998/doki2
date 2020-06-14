package com.androidproj.doki2.dao;

import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SayingDao extends JpaRepository<Saying,Integer> {
    List<Saying> findAll();
    List<Saying> findAllByIsPublic(boolean isPublic);
    List<Saying> findAllByFromUser(User user);
    List<Saying> findAllByToUser(User user);
    Saying findBySayingId(int saying_id) throws Exception;
}
