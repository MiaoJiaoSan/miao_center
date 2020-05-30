package com.miaojiaosan.material.dal.mapper;

import com.miaojiaosan.material.dal.dao.CommentDAO;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommentDAO record);

    CommentDAO selectByPrimaryKey(Long id);

    List<CommentDAO> selectAll();

    int updateByPrimaryKey(CommentDAO record);
}