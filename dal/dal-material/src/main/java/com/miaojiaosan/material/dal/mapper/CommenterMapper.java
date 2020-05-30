package com.miaojiaosan.material.dal.mapper;

import com.miaojiaosan.material.dal.dao.CommenterDAO;

import java.util.List;

public interface CommenterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommenterDAO record);

    CommenterDAO selectByPrimaryKey(Long id);

    List<CommenterDAO> selectAll();

    int updateByPrimaryKey(CommenterDAO record);
}