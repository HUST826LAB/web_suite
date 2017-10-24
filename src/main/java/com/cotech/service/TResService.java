package com.cotech.service;

import com.cotech.dao.TResDao;
import com.cotech.dao.TUserDao;
import com.cotech.model.TopTenList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TResService {
    @Resource
    private TResDao TResDao;

    public Long getResCount(){
        return TResDao.getResCount();
    }

}
