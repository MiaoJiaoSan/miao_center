package com.miaojiaosan.user.dal.mapper;

import com.miaojiaosan.user.dal.dao.UserRoleDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRoleDAO record);

    UserRoleDAO selectByPrimaryKey(Long id);

    List<UserRoleDAO> selectAll();

    int updateByPrimaryKey(UserRoleDAO record);
}