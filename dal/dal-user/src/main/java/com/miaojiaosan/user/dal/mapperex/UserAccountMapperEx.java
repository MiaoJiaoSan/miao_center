package com.miaojiaosan.user.dal.mapperex;

import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import com.miaojiaosan.user.dal.mapper.UserAccountMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号MapperEX
 * @author miaojiaosan
 * @date 2020/05/05
 */
@Mapper
public interface UserAccountMapperEx extends UserAccountMapper {

  /**
   * 账号信息
   * @param record {@link UserAccountDAO}
   * @return 行
   */
  @Override
  int insert(UserAccountDAO record);
  /**
   * 修改账号
   */
  int modify(UserAccountDAO record);
  /**
   * refreshToken
   */
  int refreshToken(UserAccountDAO record);
  /**
   * 根据账号
   * @param account 账号
   * @return {@link UserAccountDAO}
   */
  UserAccountDAO byAccount(String account);
}