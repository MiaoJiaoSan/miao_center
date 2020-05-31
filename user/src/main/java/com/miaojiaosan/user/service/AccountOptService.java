package com.miaojiaosan.user.service;

import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.PasswordOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;
import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;

/**
 * 账号
 */
public interface AccountOptService {

  /**
   * 注册
   * @param opt {@link RegistryOpt}
   * @return true 注册成功
   */
  RegistryDTO registry(RegistryOpt opt);

  /**
   * 登录
   * @param opt {@link LoginOpt}
   * @return true 登录成功
   */
  LoginDTO login(LoginOpt opt);


  /**
   * 重置密码
   * @param opt {@link PasswordOpt}
   * @return true 成功
   */
  Boolean password(PasswordOpt opt);
}
