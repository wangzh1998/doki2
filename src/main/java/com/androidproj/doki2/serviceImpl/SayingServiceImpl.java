package com.androidproj.doki2.serviceImpl;

import com.androidproj.doki2.dao.SayingDao;
import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.SayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SayingServiceImpl implements SayingService {
    @Autowired
    SayingDao sayingDao;

    public SayingServiceImpl() {
    }

    @Override
    public Saying addSaying(Saying saying) throws Exception {
        Saying result = this.sayingDao.save(saying);
        if(result == null)
            throw new Exception("保存说说失败");
        return result;
    }

    @Override
    public List<Saying> getAllPublicSayings() {
        List<Saying> result = this.sayingDao.findAllByIsPublic(true);
        return result;
    }

    @Override
    public List<Saying> getRelatedPrivateFromSayings(User user) {
        List<Saying> result = this.sayingDao.findAllByFromUser(user);
        return result;
    }

    @Override
    public List<Saying> getRelatedPrivateToSayings(User user) {
        List<Saying> result = this.sayingDao.findAllByToUser(user);
        return result;
    }

    @Override
    public Saying getSaying(int saying_id) throws Exception {
        Saying result = this.sayingDao.findBySayingId(saying_id);
        if(result == null)
            throw new Exception("查找说说失败");
        return result;
    }

}
