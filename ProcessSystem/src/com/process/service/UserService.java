package com.process.service;

import com.process.bean.UserPojo;
import com.process.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public List<UserPojo> findUserByName(String userName){return userDao.findUserByName(userName);}
}
