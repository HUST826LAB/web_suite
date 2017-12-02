package com.cotech.dao;

import com.cotech.model.GroupDetail;
import com.cotech.model.PageVo;
import com.cotech.model.TUser;
import com.cotech.model.TopTenList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("TUserDao")
public interface TUserDao {

//    @Select("SELECT * FROM t_user")
//    List<TUser> getList();

    @Select("SELECT gold,username,uname FROM t_user WHERE status !=0 ORDER BY gold DESC LIMIT 10")
    List<TopTenList> selectGoldTopTen();

    @Select("SELECT score,username,uname FROM t_user WHERE status !=0 ORDER BY score DESC LIMIT 3")
    List<TopTenList> selectScoreTopThree();

    @Select("SELECT COUNT(*) FROM t_user WHERE user_id = #{id} AND status!=0")
    Integer countUserCountById(Long id);

    @Select("SELECT user_id,score,gold,sex,blood,constellation,address,`group` FROM t_user WHERE user_id = #{id} AND status!=0")
    TUser getUserScoreAndGoldByID(Long id);

    @Update("UPDATE t_user SET gold = #{gold},score = #{score} WHERE user_id = #{user_id}")
    void updateGoldAndScoreById(TUser user);

    @Select("SELECT COUNT(*) FROM t_user WHERE username = #{username}")
    Integer countUsernameByUsername(String username);

    @Insert("INSERT INTO t_user (score,gold,`group`,username,password,ip,device,create_time,status,location_id)" +
            "VALUES (#{score},#{gold},#{group},#{username},#{password},#{ip},#{device},#{create_time},#{status},#{location_id})")
    void saveUserSignUp(TUser user);

    @Select("SELECT user_id FROM t_user where location_id=#{location_id}")
    Long getUserIdByLocation(String location_id);

    @Delete("DELETE FROM t_user WHERE user_id = #{user_id}")
    void deleteUserById(Long user_id);

    @Select("SELECT user_id,`status`,username,score,gold FROM t_user WHERE username=#{username} AND password=#{password} AND `status` !=0")
    TUser getUserSignIn(TUser user);

    @Select("SELECT `status`,username,uname,score,gold,`group` FROM t_user WHERE user_id=#{user_id} AND `status` !=0")
    TUser getUserZone(TUser user);

    @Select("SELECT user_id,username,uname,score FROM t_user WHERE status !=0 AND `group` = #{key}  ORDER BY score DESC LIMIT #{current},#{pageLen}")
    List<GroupDetail> selectUserIdByGroup(PageVo pageVo);

    @Select("SELECT count(user_id) FROM t_user WHERE `group` = #{key}")
    Long countuserByGroup(PageVo pageVo);
}
