package com.androidproj.doki2.dao;

import com.androidproj.doki2.entity.SecondReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondReplyDao extends JpaRepository<SecondReply,Integer> {
}
