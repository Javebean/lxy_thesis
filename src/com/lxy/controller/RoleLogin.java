package com.lxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lxy.service.ManagerService;
import com.lxy.service.StudentService;
import com.lxy.service.TeacherService;

@RestController
public class RoleLogin {

	@Autowired
	private ManagerService ms;
	@Autowired
	private TeacherService ts;
	@Autowired
	private StudentService ss;
	
	@RequestMapping("/rolelogin/{type}")
	public boolean roleLogin(@PathVariable int type,
			@RequestParam String username,@RequestParam String password){
		boolean login = false;
		if(type==0){//管理员
			login = ms.getManager(username, password);
		}else if(type==1){//教师
			login = ts.getTeacherByPass(username, password);
		}else if(type==2){
			login = ss.getStuByUsername(username, password);
		}
		return login;
	}
	
}
