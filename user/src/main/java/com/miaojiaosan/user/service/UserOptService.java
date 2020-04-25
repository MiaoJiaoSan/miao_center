package com.miaojiaosan.user.service;

import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;

/**
 * 用户数据操作service
 * @author miaojiaosan
 * @date: 2020/4/25
 */
public interface UserOptService {

  /**
   * 注册
   * @param registryOpt {@link RegistryOpt}
   * @return true 注册成功
   */
  Boolean registry(RegistryOpt registryOpt);

  /**
   * 登录
   * @param loginOpt {@link LoginOpt}
   * @return true 登录成功
   */
  Boolean login(LoginOpt loginOpt);
}
