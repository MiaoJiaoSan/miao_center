package com.miaojiaosan.material.dal.mapperex;

import com.miaojiaosan.material.dal.dao.MaterialDAO;
import com.miaojiaosan.material.dal.mapper.MaterialMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author miaojiaosan
 */
@Mapper
public interface MaterialMapperEx extends MaterialMapper {

  @Override
  int insert(MaterialDAO record);
}
