package com.comment.dao;

import com.comment.model.MenuRel;
import com.comment.model.MenuRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuRelMapper {
    int countByExample(MenuRelExample example);

    int deleteByExample(MenuRelExample example);

    int deleteByPrimaryKey(String sid);

    int insert(MenuRel record);

    int insertSelective(MenuRel record);

    List<MenuRel> selectByExample(MenuRelExample example);

    MenuRel selectByPrimaryKey(String sid);

    int updateByExampleSelective(@Param("record") MenuRel record, @Param("example") MenuRelExample example);

    int updateByExample(@Param("record") MenuRel record, @Param("example") MenuRelExample example);

    int updateByPrimaryKeySelective(MenuRel record);

    int updateByPrimaryKey(MenuRel record);
}