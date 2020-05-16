package com.miaojiaosan.user.dal.mapper;

import com.miaojiaosan.user.dal.dao.RoleRelDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleRelDAO record);

    RoleRelDAO selectByPrimaryKey(Long id);

    List<RoleRelDAO> selectAll();

    int updateByPrimaryKey(RoleRelDAO record);
}