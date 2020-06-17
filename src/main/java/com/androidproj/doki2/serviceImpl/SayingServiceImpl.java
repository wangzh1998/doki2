package com.androidproj.doki2.serviceImpl;

import com.androidproj.doki2.dao.SayingDao;
import com.androidproj.doki2.entity.Saying;
import com.androidproj.doki2.entity.User;
import com.androidproj.doki2.service.SayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SayingServiceImpl implements SayingService {
    @Autowired
    SayingDao sayingDao;

    public SayingServiceImpl() {
    }

    @Override
    public void addSaying(Saying saying) throws Exception {
        Saying result = this.sayingDao.save(saying);
        if(result == null)
            throw new Exception("保存说说失败");
    }

    @Override
    public Page<Saying> getPublicSayings(Integer pageNum){
        Sort sort = Sort.by(Sort.Direction.DESC,"sayingId");
        Pageable pageable = PageRequest.of(pageNum,10,sort);
        return this.sayingDao.findAllByIsPublic(true,pageable);
        //return this.sayingDao.findAll(pageable);
    }

    @Override
    public List<Saying> getRelatedPrivateFromSayings(int user_id) {
        List<Saying> result = this.sayingDao.findAllByFromUser_UniqUserId(user_id);
        return result;
    }

    @Override
    public List<Saying> getRelatedPrivateToSayings(int user_id) {
        List<Saying> result = this.sayingDao.findAllByToUser_UniqUserId(user_id);
        return result;
    }

    @Override
    public Saying getSaying(int saying_id){
        Saying result = this.sayingDao.findBySayingId(saying_id);
        return result;
    }

}
