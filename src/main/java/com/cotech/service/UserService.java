package com.cotech.service;

import com.cotech.dao.UserDao;
import com.cotech.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public List<User> getList(){
        return userDao.getList();
    }
}
