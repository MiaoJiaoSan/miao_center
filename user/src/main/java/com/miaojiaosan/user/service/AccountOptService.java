package com.miaojiaosan.user.service;

import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;

/**
 * 账号
 */
public interface AccountOptService {

  /**
   * 注册
   * @param registryDTO {@link RegistryDTO}
   * @return true 注册成功
   */
  RegistryDTO registry(RegistryDTO registryDTO);

  /**
   * 登录
   * @param loginDTO {@link LoginDTO}
   * @return true 登录成功
   */
  Boolean login(LoginDTO loginDTO);
}
