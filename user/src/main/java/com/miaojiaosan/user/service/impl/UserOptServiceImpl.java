package com.miaojiaosan.user.service.impl;

import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;
import com.miaojiaosan.user.dal.repository.UserRepository;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.service.UserOptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户数据操作service
 * @author miaojiaosan
 * @date: 2020/4/25
 */
@Service
public class UserOptServiceImpl implements UserOptService {


  @Resource
  private UserRepository userRepository;



  @Override
  public Boolean registry(RegistryOpt registryOpt) {
    Account account = new Account();
    BeanUtils.copyProperties(registryOpt, account);
    UserDO userDO = new UserDO();
    userDO.setEmail(account.getEmail());
    userDO.setPhone(account.getPhone());
    userDO.registry(account);
    return userRepository.addDataBase(userDO);
  }

  @Override
  public Boolean login(LoginOpt loginOpt) {
    Account account = new Account();
    BeanUtils.copyProperties(loginOpt, account);
    UserDO userDO = userRepository.byAccountDataBase(account);
    boolean res = false;
    if(userDO.login(account)){
      res = userRepository.addRedis(userDO);
    }
    return res;
  }


}
