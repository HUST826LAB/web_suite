package com.cotech.dao;

import com.cotech.model.TGroup;
import com.cotech.model.TopTenList;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TGroupDao")
public interface TGroupDao {

    @Select("SELECT score,name FROM t_group ORDER BY score DESC LIMIT 3")
    List<TGroup> selectGroupTopThree();
}
