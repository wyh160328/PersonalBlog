package com.process.util;

import com.process.bean.PowerBean;

import java.util.*;

public class RolePageUtil {
        private static PowerBean obj1 = new PowerBean("1","�½��","createActivity.do");
        private static PowerBean obj2 = new PowerBean("2","��鿴","activityView.do");
        private static PowerBean obj3 = new PowerBean("3","�����","checkActivity.do");
        private static PowerBean obj4 = new PowerBean("4","��������","checkFlow.do");
        private static PowerBean obj5 = new PowerBean("5","�����","sendActivity.do");
        private static PowerBean obj6 = new PowerBean("6","Ȩ������","applypermission.do");
        private static PowerBean obj7 = new PowerBean("7","Ȩ������","checkpermission.do");
        private static PowerBean obj8 = new PowerBean("8","�û�����","userset.do");
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
            normalMap.put("��ͨ�û�",normalList);
            List<PowerBean> adminList = new ArrayList<PowerBean>();
            adminList.add(obj1);
            adminList.add(obj2);
            adminList.add(obj3);
            adminList.add(obj4);
            adminList.add(obj5);
            adminList.add(obj6);
            adminList.add(obj7);
            adminList.add(obj8);
            adminMap.put("����Ա",adminList);
            if(isAdmin){
                return adminMap;
            }else{
                return normalMap;
            }
        }
}
