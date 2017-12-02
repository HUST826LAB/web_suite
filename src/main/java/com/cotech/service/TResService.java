package com.cotech.service;

import com.cotech.dao.TResDao;
import com.cotech.dao.TUserDao;
import com.cotech.model.GroupDetail;
import com.cotech.model.PageVo;
import com.cotech.model.TRes;
import com.cotech.model.TopTenList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class TResService {
    @Resource
    private TResDao TResDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Long countResCount(){
        return TResDao.countResCount();
    }

    public Long countResCountGroup(TRes res){
        return TResDao.countResCountGroup(res);
    }

    public void saveGameMain(TRes res){
        logger.debug("res参数"+res.toString());
        TResDao.saveGameMain(res);
    }

    public TRes getResByID(Long res_id){
        return TResDao.getResByID(res_id);
    }

    public Long getResIdByLocationId(String location_id){
        return TResDao.getResIdByLocationId(location_id);
    }
    public void updateResDetail(TRes TRes){
        logger.debug("res参数"+TRes.toString());
        TResDao.updateResDetail(TRes);
    }

    public void updateResUserId(TRes res){
        TResDao.updateResUserId(res);
    }

    public Long countSumGreaterScore(Long score){
        return TResDao.countSumGreaterScore(score);
    }

    public Long countSumGroupGreaterScore(TRes res){
        return TResDao.countSumGroupGreaterScore(res);
    }

    public Long countResCountCookieId(TRes res){
        return TResDao.countResCountCookieId(res);
    }

    public LinkedList<GroupDetail> selectSumbyUserId(String user_ids){
        return TResDao.selectSumbyUserId(user_ids);
    }

    public List<TRes> selectResByUserID(PageVo pageVo){
        return TResDao.selectResByUserID(pageVo);
    }

    public Long countResUserId(PageVo pageVo){
        return TResDao.countResUserId(pageVo);
    }
}
