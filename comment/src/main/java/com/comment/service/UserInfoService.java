package com.comment.service;

import java.util.List;

import com.comment.model.BabyUser;

public interface UserInfoService {

	BabyUser getUserById(String sid);
	
	List<BabyUser> getUsers();	
	
	int insertUser(BabyUser user);
}
