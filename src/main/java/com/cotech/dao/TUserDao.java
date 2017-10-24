package com.cotech.dao;

import com.cotech.model.TopTenList;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TUserDao")
public interface TUserDao {

//    @Select("SELECT * FROM t_user")
//    List<TUser> getList();

    @Select("SELECT gold,username,uname FROM t_user ORDER BY gold DESC LIMIT 10")
    List<TopTenList> getGoldTopTen();

    @Select("SELECT score,username,uname FROM t_user ORDER BY score DESC LIMIT 10")
    List<TopTenList> getScoreTopTen();
}
