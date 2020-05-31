package com.miaojiaosan.material.dal.mapperex;

import com.miaojiaosan.material.cmd.qry.MaterialListQry;
import com.miaojiaosan.material.dal.dao.MaterialDAO;
import com.miaojiaosan.material.dal.mapper.MaterialMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author miaojiaosan
 */
@Mapper
public interface MaterialMapperEx extends MaterialMapper {

  @Override
  int insert(MaterialDAO record);

  int modify(MaterialDAO record);


  List<MaterialDAO> list(MaterialListQry qry);
}
