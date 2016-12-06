package com.comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.comment.dao.BabyUserMapper;
import com.comment.model.BabyUser;
import com.comment.service.UserInfoService;


@Service("userService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private BabyUserMapper mapper;
	
	@Override
	public BabyUser getUserById(String sid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(sid);
	}

	@Override
	public List<BabyUser> getUsers() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

	@Override
	public int insertUser(BabyUser user) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(user);
	}

}
