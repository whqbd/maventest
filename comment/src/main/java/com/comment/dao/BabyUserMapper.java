package com.comment.dao;

import java.util.List;

import com.comment.model.BabyUser;

public interface BabyUserMapper {
    int deleteByPrimaryKey(String sid);

    int insert(BabyUser record);

    int insertSelective(BabyUser record);

    BabyUser selectByPrimaryKey(String sid);
    List<BabyUser> selectAll();

    int updateByPrimaryKeySelective(BabyUser record);

    int updateByPrimaryKey(BabyUser record);
}