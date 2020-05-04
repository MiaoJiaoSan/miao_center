package com.miaojiaosan.user.service;

import com.miaojiaosan.user.service.dto.AccountDTO;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
public interface AccountQryService {

  /**
   * 根据id查询账号
   * @param id 账号id
   * @return 账号VO
   */
  AccountDTO byId(Long id);

  /**
   * 根据账号查询
   * @param account 账号
   * @return 账号VO
   */
  AccountDTO byAccount(String account);

  /**
   * 根据邮件查询账号
   * @param email 邮件
   * @return 账号VO
   */
  AccountDTO byEmail(String email);

  /**
   * 根据电话查询账号
   * @param phone 电话
   * @return 账号VO
   */
  AccountDTO byPhone(String phone);

}
