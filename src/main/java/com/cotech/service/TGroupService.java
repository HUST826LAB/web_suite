package com.cotech.service;

import com.cotech.dao.TGroupDao;
import com.cotech.dao.TUserDao;
import com.cotech.model.TGroup;
import com.cotech.model.TUser;
import com.cotech.model.TopTenList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TGroupService {
    @Resource
    private TGroupDao TGroupDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<TGroup> selectGroupTopThree(){
        return TGroupDao.selectGroupTopThree();
    }

}
