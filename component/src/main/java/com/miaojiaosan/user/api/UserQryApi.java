package com.miaojiaosan.user.api;

import com.miaojiaosan.common.Result;

/**
 * @author miaojiaosan
 * @date: 2020/4/25
 */
public interface UserQryApi {
  /**
   * 根据id查询用户信息
   * @param id 用户id
   * @return 用户信息
   */
  Result<?> byId(Long id);
  /**
   * 根据账号查询用户信息
   * @param account 用户账号
   * @return 用户信息
   */
  Result<?> byAccount(String account);
}
