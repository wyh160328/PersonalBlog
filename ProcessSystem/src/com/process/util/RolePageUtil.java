package com.process.util;

import com.process.bean.PowerBean;

import java.util.*;

public class RolePageUtil {
        private static PowerBean obj1 = new PowerBean("1","新建活动","createActivity.do");
        private static PowerBean obj2 = new PowerBean("2","活动查看","activityView.do");
        private static PowerBean obj3 = new PowerBean("3","活动审批","checkActivity.do");
        private static PowerBean obj4 = new PowerBean("4","审批流程","checkFlow.do");
        private static PowerBean obj5 = new PowerBean("5","活动推送","sendActivity.do");
        private static PowerBean obj6 = new PowerBean("6","权限申请","applypermission.do");
        private static PowerBean obj7 = new PowerBean("7","权限审批","checkpermission.do");
        private static PowerBean obj8 = new PowerBean("8","用户管理","userset.do");
        public static Map<String, List<PowerBean>> getRolePage(boolean isAdmin){
            Map<String, List<PowerBean>> normalMap = new LinkedHashMap<>();
            Map<String, List<PowerBean>> adminMap =new LinkedHashMap<>();;
            List<PowerBean> normalList = new ArrayList<PowerBean>();
            normalList.add(obj1);
            normalList.add(obj2);
            normalList.add(obj3);
            normalList.add(obj4);
            normalList.add(obj5);
            normalList.add(obj6);
            normalMap.put("普通用户",normalList);
            List<PowerBean> adminList = new ArrayList<PowerBean>();
            adminList.add(obj1);
            adminList.add(obj2);
            adminList.add(obj3);
            adminList.add(obj4);
            adminList.add(obj5);
            adminList.add(obj6);
            adminList.add(obj7);
            adminList.add(obj8);
            adminMap.put("管理员",adminList);
            if(isAdmin){
                return adminMap;
            }else{
                return normalMap;
            }
        }
}
