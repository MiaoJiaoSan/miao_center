package com.miaojiaosan.user.service.impl;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.service.UserOptService;
import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import com.miaojiaosan.user.service.processor.LoginProcessor;
import com.miaojiaosan.user.service.processor.RegistryProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户数据操作service
 *
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Service
public class UserOptServiceImpl implements UserOptService {


  @Resource
  private RegistryProcessor registryProcessor;

  @Resource
  private LoginProcessor loginProcessor;

  @Override
  public Boolean registry(RegistryDTO registryDTO) {
    UserDO userDO = registryProcessor.prepare(registryDTO);
    userDO.registry();
    Boolean rst = registryProcessor.process(userDO);
    registryProcessor.completable(userDO);
    return rst;
  }

  @Override
  public Boolean login(LoginDTO loginDTO) {
    UserDO userDO = loginProcessor.prepare(loginDTO);
    boolean loginRst = userDO.login(userDO.getAccount());
    Boolean rst = loginProcessor.process(userDO);
    loginProcessor.completable(userDO);
    return loginRst&&rst;
  }

}
