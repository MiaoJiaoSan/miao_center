package com.miaojiaosan.user.api;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;

/**
 * @author miaojiaosan
 * @date: 2020/4/25
 */
public interface UserOptApi {
  /**
   * 注册
   * @param registryOpt {@link RegistryOpt}
   * @return true 注册成功
   */
  Result<Boolean> registry(RegistryOpt registryOpt);
  /**
   * 登录
   * @param loginOpt {@link LoginOpt}
   * @return true 登录成功
   */
  Result<Boolean> login(LoginOpt loginOpt);
}
