package com.cotech.dao;

import com.cotech.model.TRes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository("TResDao")
public interface TResDao {

    @Select("SELECT count(*) FROM t_res")
    Long getResCount();

    @Insert("INSERT INTO t_res (coordinate,time_len,deviation,score,gold,device,ip,create_time,`group`,location_id)" +
            "VALUES (#{coordinate},#{time_len},#{deviation},#{score},#{gold},#{device},#{ip},#{create_time},#{group},#{location_id})")
    void saveGameMain(TRes tRes);

    @Select("SELECT res_id FROM t_res WHERE location_id = #{location_id} LIMIT 1")
    Long getResIdByLocationId(String location_id);
}
