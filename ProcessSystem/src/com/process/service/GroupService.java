package com.process.service;

import com.process.bean.GroupPojo;
import com.process.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;
    public List<GroupPojo> findAllGroup(){
        return groupDao.findAllGroup();
    }
    public List<GroupPojo> findGroupById(String id){
        return groupDao.findGroupById(id);
    }
    public void deleteGroupById(String id){
        groupDao.deleteGroupById(id);
    }
    public void updataGroup(GroupPojo groupPojo){
        updataGroup(groupPojo);
    }
}
