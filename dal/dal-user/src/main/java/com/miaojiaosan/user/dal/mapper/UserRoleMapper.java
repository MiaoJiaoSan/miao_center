package com.miaojiaosan.user.dal.mapper;

import com.miaojiaosan.user.dal.dao.UserRoleDAO;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRoleDAO record);

    UserRoleDAO selectByPrimaryKey(Long id);

    List<UserRoleDAO> selectAll();

    int updateByPrimaryKey(UserRoleDAO record);
}