package com.cotech.service;

import com.cotech.dao.TUserDao;
import com.cotech.model.TUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TUserService {
    @Resource
    private TUserDao tuserDao;

    public List<TUser> getList(){
        return tuserDao.getList();
    }
}
