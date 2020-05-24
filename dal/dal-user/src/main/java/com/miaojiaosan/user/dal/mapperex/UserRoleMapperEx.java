package com.miaojiaosan.user.dal.mapperex;

import com.miaojiaosan.user.dal.dao.UserRoleDAO;
import com.miaojiaosan.user.dal.mapper.UserRoleMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapperEx extends UserRoleMapper {

  UserRoleDAO byCode(String code);

  List<UserRoleDAO> byIds(@Param("ids") List<Long> ids);


}
