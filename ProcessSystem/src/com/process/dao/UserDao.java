package com.process.dao;

import com.process.bean.UserPojo;

import java.util.List;

public interface UserDao {
    public List<UserPojo> findUserByName(String userName);
}
