package com.miaojiaosan.user.service;

import com.miaojiaosan.user.cmd.opt.PersonChangeOpt;

/**
 * 用户数据操作service
 * @author miaojiaosan
 * @date 2020/4/25
 */
public interface UserOptService {

  /**
   * 用户修改用户信息
   * @param opt {@link PersonChangeOpt}
   * @return true 成功
   */
  Boolean change(PersonChangeOpt opt);
}
