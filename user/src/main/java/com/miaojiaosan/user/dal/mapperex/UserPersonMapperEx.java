package com.miaojiaosan.user.dal.mapperex;

import com.miaojiaosan.user.dal.dao.UserPersonDAO;
import com.miaojiaosan.user.dal.mapper.UserPersonMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPersonMapperEx extends UserPersonMapper {


  int insert(UserPersonDAO record);


}