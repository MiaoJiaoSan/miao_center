package com.miaojiaosan.user.service.impl;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.service.AccountOptService;
import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.PasswordDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import com.miaojiaosan.user.service.processor.LoginProcessor;
import com.miaojiaosan.user.service.processor.PasswordProcessor;
import com.miaojiaosan.user.service.processor.RegistryProcessor;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountOptServiceImpl implements AccountOptService {


  @Resource
  private RegistryProcessor registryProcessor;

  @Resource
  private LoginProcessor loginProcessor;

  @Resource
  private PasswordProcessor passwordProcessor;

  @Resource
  private Mapper mapper;

  @Override
  public RegistryDTO registry(RegistryDTO registryDTO) {
    UserDO userDO = registryProcessor.prepare(registryDTO);
    userDO.registry(registryDTO);
    registryProcessor.process(userDO);
    userDO.registryAfter(registryDTO);
    registryProcessor.completable(userDO);
    return mapper.map(userDO.getAccount(), RegistryDTO.class);
  }

  @Override
  public LoginDTO login(LoginDTO loginDTO) {
    UserDO userDO = loginProcessor.prepare(loginDTO);
    userDO.login(loginDTO);
    loginProcessor.process(userDO);
    loginProcessor.completable(userDO);
    return mapper.map(userDO.getAccount(), LoginDTO.class);

  }

  @Override
  public Boolean password(PasswordDTO passwordDTO) {
    UserDO userDO = passwordProcessor.prepare(passwordDTO);
    userDO.password(passwordDTO);
    passwordProcessor.process(userDO);
    loginProcessor.completable(userDO);
    return true;
  }
}
