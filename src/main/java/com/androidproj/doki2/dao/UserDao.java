package com.androidproj.doki2.dao;

import com.androidproj.doki2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    List<User> findAll();
    User findUserByUniqUserId(int user_id);
    //User findUserByUniq_user_id(int user_id);
    User findUserByPhoneNumber(String phone_num);
}
