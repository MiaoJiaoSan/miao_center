package com.miaojiaosan.user.dal.mapper;

import com.miaojiaosan.user.dal.dao.UserPersonDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPersonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPersonDAO record);

    UserPersonDAO selectByPrimaryKey(Long id);

    List<UserPersonDAO> selectAll();

    int updateByPrimaryKey(UserPersonDAO record);
}