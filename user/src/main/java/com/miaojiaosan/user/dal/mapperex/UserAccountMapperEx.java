package com.miaojiaosan.user.dal.mapperex;

import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import com.miaojiaosan.user.dal.mapper.UserAccountMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapperEx extends UserAccountMapper {

  @Override
  int insert(UserAccountDAO record);

  UserAccountDAO byAccount(String account);
}