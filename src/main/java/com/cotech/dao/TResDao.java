package com.cotech.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository("TResDao")
public interface TResDao {

    @Select("SELECT count(*) FROM t_res")
    Long getResCount();
}
