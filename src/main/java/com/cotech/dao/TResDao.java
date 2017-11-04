package com.cotech.dao;

import com.cotech.model.TRes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository("TResDao")
public interface TResDao {

    @Select("SELECT count(*) FROM t_res")
    Long countResCount();

    @Select("SELECT count(*) FROM t_res WHERE `group` = #{group}")
    Long countResCountGroup(TRes res);

    @Select("SELECT ip,device,res_id,user_id,referee,score,gold,`group` FROM t_res WHERE res_id = #{res_id}")
    TRes getResByID(Long res_id);

    @Insert("INSERT INTO t_res (cookie_id,user_id,coordinate,time_len,deviation,score,gold,device,ip,create_time,`group`,location_id,referee)" +
            "VALUES (#{cookie_id},#{user_id},#{coordinate},#{time_len},#{deviation},#{score},#{gold},#{device},#{ip},#{create_time},#{group},#{location_id},#{referee})")
    void saveGameMain(TRes tRes);

    @Update("UPDATE t_res SET user_id=#{user_id},constellation=#{constellation},blood=#{blood},address=#{address},sex=#{sex} WHERE res_id = #{res_id}")
    void updateResDetail(TRes tRes);

    @Update("UPDATE t_res SET user_id=#{user_id} where res_id=#{res_id}")
    void updateResUserId(TRes res);

    @Select("SELECT res_id FROM t_res WHERE location_id = #{location_id} LIMIT 1")
    Long getResIdByLocationId(String location_id);

    @Select("SELECT COUNT(*) FROM t_res WHERE score > #{score}")
    Long countSumGreaterScore(Long score);

    @Select("SELECT COUNT(*) FROM t_res WHERE score > #{score} AND `group` = #{group}")
    Long conutSumGroupGreaterScore(TRes res);
}
