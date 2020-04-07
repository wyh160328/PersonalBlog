package com.process.dao;

import com.process.bean.GroupPojo;

import java.util.List;

public interface GroupDao {
    public List<GroupPojo> findAllGroup();
    public List<GroupPojo> findGroupById(String id);
    public void deleteGroupById(String id);
    public void updataGroup(GroupPojo groupPojo);
}
