package com.cotech.dao;

import com.cotech.model.TUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface TUserDao {

    @Select("SELECT * FROM t_user")
    List<TUser> getList();
}
