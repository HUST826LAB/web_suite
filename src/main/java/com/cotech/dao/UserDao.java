package com.cotech.dao;

import com.cotech.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface UserDao {

    @Select("SELECT * FROM t_user")
    List<User> getList();
}
