package com.process.controller;

import com.process.bean.GroupPojo;
import com.process.bean.UserPojo;
import com.process.service.GroupService;
import com.process.service.UserService;
import com.process.util.RolePageUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class LoginController {
	private static Logger LOG = Logger.getLogger(LoginController.class);
	// 跳转到登陆页面
	@RequestMapping("loginservlet.do")
	public String forward(HttpServletRequest request) {

		return "login";
	}

	@RequestMapping("welcome.do")
	public String go() {
		return "welcome";
	}

	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	// 登陆验证
	@RequestMapping("logincheck.do")
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LOG.info("系统进入logincheck.do");
		// 设置编码格式
		// request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html;charset=UTF-8");
		// 获取账号密码
		String user = request.getParameter("user");
		LOG.info(user);
		String password = request.getParameter("password");
		LOG.info(password);
		String imgcode = request.getParameter("imge");
		LOG.info(imgcode);
		String imgcodes = (String) request.getSession().getAttribute("random");
		LOG.error("aa" + imgcodes);
		if (imgcode.equalsIgnoreCase(request.getSession().getAttribute("random").toString())) {
			List<UserPojo> userByName = userService.findUserByName(user);
			if(userByName.size() > 0){
				if(userByName.get(0).getPassword().equals(password)){
					request.getSession().setAttribute("username",userByName.get(0).getName());
					String userPermission = userByName.get(0).getUserPermission();
					boolean isAdmin = false;
					String[] split = userPermission.split("&");
					for(String s:split){
						List<GroupPojo> groupById = groupService.findGroupById(s);
						if(groupById.size()>0){
							String permission = groupById.get(0).getPermission();
							if(permission.indexOf("admin")>-1){
								isAdmin = true;
							}
						}
					}
					request.getSession().setAttribute("map", RolePageUtil.getRolePage(isAdmin));
					return "index";
				}else {
					return "login";
				}
			}else{
				return "login";
			}
		}
		return "login";
	}
	@RequestMapping("log-off.do")
	public String logOff(HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}
}
