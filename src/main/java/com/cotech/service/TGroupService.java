package com.cotech.service;

import com.cotech.dao.TGroupDao;
import com.cotech.dao.TUserDao;
import com.cotech.model.PageVo;
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

    public List<TopTenList> selectGroupTopThree(){
        return TGroupDao.selectGroupTopThree();
    }

    public String getGroupName(String group_id){
        return TGroupDao.getGroupName(group_id);
    }

    public void saveGroup(TGroup group){
        TGroupDao.saveGroup(group);
    }

    public List<TGroup> selectGroupByPage(PageVo pageVo){
        return TGroupDao.selectGroupByPage(pageVo);
    }

    public Long countGroup(){
        return TGroupDao.countGroup();
    }

    public Long countGroupName(TGroup group){
        return TGroupDao.countGroupName(group);
    }

    public String getGroup_id(TGroup group){
        return TGroupDao.getGroup_id(group);
    }

    public void deleteGroupByName(TGroup group){
        TGroupDao.deleteGroupByName(group);
    }

    public TGroup getGroup(String group_id){
        return TGroupDao.getGroup(group_id);
    }
    public void updateScoreById(TGroup group){
        TGroupDao.updateScoreById(group);
    }

    public void updateNumById(TGroup group){
        TGroupDao.updateNumById(group);
    }


}
