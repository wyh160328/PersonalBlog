package com.process.controller;

import com.process.bean.UserPojo;
import com.process.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestController {
    private static Logger LOG = Logger.getLogger(TestController.class);
    @Autowired
    private UserService userService;
    @RequestMapping("test.do")
    public String test(HttpServletRequest request){
        String name = request.getParameter("name");
        List<UserPojo> list = userService.findUserByName(name);
        if(list.size() > 0){
            LOG.info(list.get(0).getName());
            LOG.info(list.size());
        }
        return "test";
    }
}
