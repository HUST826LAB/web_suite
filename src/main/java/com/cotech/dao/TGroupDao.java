package com.cotech.dao;

import com.cotech.model.PageVo;
import com.cotech.model.TGroup;
import com.cotech.model.TopTenList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TGroupDao")
public interface TGroupDao {

    @Select("SELECT score,name FROM t_group ORDER BY score DESC LIMIT 3")
    List<TopTenList> selectGroupTopThree();

    @Select("SELECT group_id,score,name,numSum,owner,creator,create_time FROM t_group LIMIT #{current},#{pageLen}")
    List<TGroup> selectGroupByPage(PageVo pageVo);

    @Select("SELECT count(group_id) FROM t_group")
    Long countGroup();

    @Select("SELECT name FROM t_group WHERE group_id = #{group_id}")
    String getGroupName(String group_id);

    @Insert("INSERT INTO t_group (group_id,name,score,numSum,create_time,owner,creator) VALUES" +
            " (#{group_id},#{name},#{score},#{numSum},#{create_time},#{owner},#{creator})")
    void saveGroup(TGroup group);
}
