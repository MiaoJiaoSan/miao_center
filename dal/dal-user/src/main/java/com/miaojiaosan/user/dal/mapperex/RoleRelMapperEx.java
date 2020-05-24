package com.miaojiaosan.user.dal.mapperex;

import com.miaojiaosan.user.dal.dao.RoleRelDAO;
import com.miaojiaosan.user.dal.mapper.RoleRelMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleRelMapperEx extends RoleRelMapper {

    @Override
    int insert(RoleRelDAO record);

    int batchInsert(@Param("records") List<RoleRelDAO> records);

    List<RoleRelDAO> byAccountId(Long userId);
}