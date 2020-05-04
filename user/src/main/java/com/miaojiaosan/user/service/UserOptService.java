package com.miaojiaosan.user.service;

import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;

/**
 * 用户数据操作service
 * @author miaojiaosan
 * @date 2020/4/25
 */
public interface UserOptService {

  /**
   * 注册
   * @param registryDTO {@link RegistryDTO}
   * @return true 注册成功
   */
  Boolean registry(RegistryDTO registryDTO);

  /**
   * 登录
   * @param loginDTO {@link LoginDTO}
   * @return true 登录成功
   */
  Boolean login(LoginDTO loginDTO);
}
