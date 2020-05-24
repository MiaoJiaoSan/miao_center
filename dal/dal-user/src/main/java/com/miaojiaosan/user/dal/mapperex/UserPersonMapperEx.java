package com.miaojiaosan.user.dal.mapperex;

import com.miaojiaosan.user.dal.dao.UserPersonDAO;
import com.miaojiaosan.user.dal.mapper.UserPersonMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户MapperEX
 * @author miaojiaosan
 * @date 2020/05/05
 */
@Mapper
public interface UserPersonMapperEx extends UserPersonMapper {

  /**
   * 新增
   * @param record {@link UserPersonDAO}
   * @return 行
   */
  @Override
  int insert(UserPersonDAO record);


}