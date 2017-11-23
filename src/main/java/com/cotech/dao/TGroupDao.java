package com.cotech.dao;

import com.cotech.model.PageVo;
import com.cotech.model.TGroup;
import com.cotech.model.TUser;
import com.cotech.model.TopTenList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TGroupDao")
public interface TGroupDao {

    @Select("SELECT score,name FROM t_group ORDER BY score DESC LIMIT 3")
    List<TopTenList> selectGroupTopThree();

    @Select("SELECT group_id,score,name,numSum,owner,creator,create_time FROM t_group LIMIT #{current},#{pageLen}")
    List<TGroup> selectGroupByPage(PageVo pageVo);

    @Select("SELECT group_id,score,name,numSum,owner,creator,create_time FROM t_group WHERE group_id=#{group_id}")
    TGroup getGroup(String group_id);

    @Select("SELECT count(group_id) FROM t_group")
    Long countGroup();

    @Select("SELECT name FROM t_group WHERE group_id = #{group_id}")
    String getGroupName(String group_id);

    @Select("SELECT group_id FROM t_group WHERE name = #{name}")
    String getGroup_id(TGroup group);

    @Select("SELECT count(group_id) FROM t_group WHERE name = #{name}")
    Long countGroupName(TGroup group);

    @Insert("INSERT INTO t_group (group_id,name,score,numSum,create_time,owner,creator) VALUES" +
            " (#{group_id},#{name},#{score},#{numSum},#{create_time},#{owner},#{creator})")
    void saveGroup(TGroup group);

    @Delete("DELETE FROM t_group WHERE name = #{name}")
    void deleteGroupByName(TGroup group);

    @Update("UPDATE t_group SET score = #{score} WHERE group_id = #{group_id}")
    void updateScoreById(TGroup group);

    @Update("UPDATE t_group SET numSum = #{numSum} WHERE group_id = #{group_id}")
    void updateNumById(TGroup group);
}
