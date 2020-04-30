package com.miaojiaosan.user.service.impl;

import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;
import com.miaojiaosan.user.dal.repository.UserRepository;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.LoginEvent;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.service.UserOptService;
import org.dozer.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户数据操作service
 *
 * @author miaojiaosan
 * @date: 2020/4/25
 */
@Service
public class UserOptServiceImpl implements UserOptService {

  @Resource
  private ApplicationEventPublisher eventPublisher;
  @Resource
  private UserRepository userRepository;
  @Resource
  private Mapper mapper;


  @Override
  public Boolean registry(RegistryOpt registryOpt) {
    Account account = mapper.map(registryOpt,Account.class);
    UserDO userDO = new UserDO();
    userDO.setEmail(account.getEmail());
    userDO.setPhone(account.getPhone());
    userDO.setAccount(account);
    boolean rst = userRepository.addDataBase(userDO);
    if (rst) {
      userDO.registry();
      eventPublisher.publishEvent(new RegistryEvent(userDO));
    }
    return rst;

  }

  @Override
  public Boolean login(LoginOpt loginOpt) {
    Account account = mapper.map(loginOpt,Account.class);
    UserDO userDO = userRepository.byAccountDataBase(account);
    boolean res = false;
    if (userDO.login(account)) {
      res = userRepository.addRedis(userDO);
      eventPublisher.publishEvent(new LoginEvent(userDO));
    }
    return res;
  }


}
