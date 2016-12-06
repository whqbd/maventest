package com.comment.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comment.model.BabyUser;
import com.comment.service.UserInfoService;

@Controller
@RequestMapping("/user")
public class UserController {
	private Logger logger= Logger.getLogger(UserController.class);

	@Autowired
	private UserInfoService service;
	
	
	@RequestMapping("/getAll")
	@ResponseBody
	public Object showInfoUser(ModelMap modelMap,String userId){
		logger.error(">>>>>>>>>>>进入getAll");
		logger.error("userid>.>>"+userId);
		List<BabyUser> list=service.getUsers();
		return list;
	}
}
