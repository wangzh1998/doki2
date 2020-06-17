package com.androidproj.doki2.service;

import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;


public interface SayingService {
    //添加说说
    void addSaying(Saying saying) throws Exception;

    //读取所有的公共说说 分页
    Page<Saying> getPublicSayings(Integer pageNum);

    //读取和用户相关的说说
    //TODO 这里是用user做参数还是用user_id做参数呢？user_id
    List<Saying> getRelatedPrivateFromSayings(int user_id);
    List<Saying> getRelatedPrivateToSayings(int user_id);

    //根据saying_id获取一条唯一的说说
    Saying getSaying(int saying_id);
}
