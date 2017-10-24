package com.cotech.service;

import com.cotech.dao.TUserDao;
import com.cotech.model.TUser;
import com.cotech.model.TopTenList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class TUserService {
    @Resource
    private TUserDao TUserDao;

//    public List<TUser> getList(){
//        return TUserDao.getList();
//    }

    public List<TopTenList> getGoldTopTen(){
        return  TUserDao.getGoldTopTen();
    }

    public List<TopTenList> getScoreTopTen(){
        return  TUserDao.getScoreTopTen();
    }
}
