package com.miaojiaosan.user.dal.mapper;

import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 账号Mapper
 * @author miaojiaosan
 * @date 2020/05/05
 */
@Mapper
public interface UserAccountMapper {

  /**
   * 删除
   * @param id 主键
   * @return 行
   */
  int deleteByPrimaryKey(Long id);

  /**
   * 新增
   * @param record {@link UserAccountDAO}
   * @return 行
   */
  int insert(UserAccountDAO record);

  /**
   * 账号信息
   * @param id 主键
   * @return {@link UserAccountDAO}
   */
  UserAccountDAO selectByPrimaryKey(Long id);

  /**
   * 账号信息列表
   * @return {@link UserAccountDAO}
   */
  List<UserAccountDAO> selectAll();

  /**
   * 更新
   * @param record {@link UserAccountDAO}
   * @return 行
   */
  int updateByPrimaryKey(UserAccountDAO record);
}