package com.comment.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.comment.model.BabyUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class TestUserService {

	
	private Logger logger= Logger.getLogger(TestUserService.class);
	@Autowired
	private UserInfoService service;

//	
	@Test
	public void getUser(){
		System.out.println("2222");
		List<BabyUser> list=service.getUsers();
		logger.error(JSON.toJSON(list));
		
		BabyUser user=service.getUserById("0967d3460a444f7d8d84e2cce1fcf0df");
		logger.error(">>>>>user"+JSON.toJSONString(user));
	}
	
}
