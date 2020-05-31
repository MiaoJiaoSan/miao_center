package com.miaojiaosan.material.dal.mapper;

import com.miaojiaosan.material.dal.dao.MaterialDAO;

import java.util.List;

public interface MaterialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialDAO record);

    MaterialDAO selectByPrimaryKey(Long id);

    List<MaterialDAO> selectAll();

    int updateByPrimaryKey(MaterialDAO record);
}