package com.miaojiaosan.user.dal.mapper;

import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAccountMapper {
  int deleteByPrimaryKey(Long id);

  int insert(UserAccountDAO record);

  UserAccountDAO selectByPrimaryKey(Long id);

  List<UserAccountDAO> selectAll();

  int updateByPrimaryKey(UserAccountDAO record);
}