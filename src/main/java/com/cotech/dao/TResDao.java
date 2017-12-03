package com.cotech.dao;

import com.cotech.model.GroupDetail;
import com.cotech.model.PageVo;
import com.cotech.model.TRes;
import com.cotech.model.TUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;


@Repository("TResDao")
public interface TResDao {

    @Select("SELECT count(distinct cookie_id) FROM t_res")
    Long countResCount();

    @Select("SELECT COUNT(distinct cookie_id) FROM t_res WHERE `group` = #{group}")
    Long countResCountGroup(TRes res);

    @Select("SELECT COUNT(distinct cookie_id) FROM t_res")
    Long countResCountCookieId(TRes res);

    @Select("SELECT ip,device,res_id,user_id,referee,score,gold,`group` FROM t_res WHERE res_id = #{res_id}")
    TRes getResByID(Long res_id);

    @Insert("INSERT INTO t_res (blood,cookie_id,user_id,address,coordinate,time_len,deviation,score,gold,device,ip,create_time,`group`,location_id,referee)" +
            "VALUES (#{blood},#{cookie_id},#{user_id},#{address},#{coordinate},#{time_len},#{deviation},#{score},#{gold},#{device},#{ip},#{create_time},#{group},#{location_id},#{referee})")
    void saveGameMain(TRes tRes);

    @Update("UPDATE t_res SET user_id=#{user_id},constellation=#{constellation},blood=#{blood},sex=#{sex} WHERE res_id = #{res_id}")
    void updateResDetail(TRes tRes);

    @Update("UPDATE t_res SET user_id=#{user_id} where res_id=#{res_id}")
    void updateResUserId(TRes res);

    @Select("SELECT res_id FROM t_res WHERE location_id = #{location_id} LIMIT 1")
    Long getResIdByLocationId(String location_id);

    @Select("SELECT COUNT(distinct cookie_id) FROM t_res WHERE score > #{score}")
    Long countSumGreaterScore(Long score);

    @Select("SELECT COUNT(distinct cookie_id) FROM t_res WHERE score > #{score} AND `group` = #{group}")
    Long countSumGroupGreaterScore(TRes res);

    @Select("SELECT COUNT(res_id) AS sum,user_id,MAX(create_time) AS lastTime FROM t_res GROUP BY user_id HAVING user_id IN (${user_ids})")
    LinkedList<GroupDetail> selectSumbyUserId(@Param("user_ids")String user_ids);

    @Select("SELECT res_id,user_id,score,create_time,time_Len,blood FROM t_res WHERE user_id = #{key} ORDER BY create_time DESC LIMIT #{current},#{pageLen}")
    List<TRes> selectResByUserID(PageVo pageVo);

    @Select("SELECT COUNT(res_id) FROM t_res WHERE user_id = #{key}")
    Long countResUserId(PageVo pageVo);

    @Select("SELECT res_id,coordinate,address,blood FROM t_res WHERE res_id = #{res_id}")
    TRes getGameDetail(TRes res);

    //just for wrap
//    @Select("SELECT res_id,blood,address FROM t_res")
//    LinkedList<TRes> selectAll();
//    @Update("UPDATE t_res SET blood = #{blood} , address = #{address} WHERE res_id = #{res_id}")
//    void updateAll(TRes res);

}
