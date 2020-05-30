package com.miaojiaosan.user.api;

import com.miaojiaosan.common.Result;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
public interface AccountQryApi {
  /**
   * 根据id查询账号信息
   * @param id 账号id
   * @return 账号信息
   */
  Result<?> byId(Long id);

  /**
   * 根据账号查询账号信息
   * @param account 账号
   * @return 账号信息
   */
  Result<?> byAccount(String account);
  /**
   * 根据邮箱查询账号信息
   * @param email 邮箱
   * @return 账号信息
   */
  Result<?> byEmail(String email);
  /**
   * 根据电话查询账号信息
   * @param phone 电话
   * @return 账号信息
   */
  Result<?> byPhone(String phone);
}
