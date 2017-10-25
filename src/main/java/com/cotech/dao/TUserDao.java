package com.cotech.dao;

import com.cotech.model.TUser;
import com.cotech.model.TopTenList;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TUserDao")
public interface TUserDao {

//    @Select("SELECT * FROM t_user")
//    List<TUser> getList();

    @Select("SELECT gold,username,uname FROM t_user WHERE status !=0 ORDER BY gold DESC LIMIT 10")
    List<TopTenList> getGoldTopTen();

    @Select("SELECT score,username,uname FROM t_user WHERE status !=0 ORDER BY score DESC LIMIT 10")
    List<TopTenList> getScoreTopTen();

    @Select("SELECT COUNT(*) FROM t_user WHERE user_id = #{id} AND status!=0")
    Integer getUserCountById(Long id);

    @Select("SELECT user_id,score,gold FROM t_user WHERE user_id = #{id} AND status!=0")
    TUser getUserScoreAndGoldByID(Long id);

    @Update("UPDATE t_user SET gold = #{gold},score = #{score} WHERE user_id = #{user_id}")
    void updateGoldAndScoreById(TUser user);
}
