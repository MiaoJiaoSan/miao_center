package com.miaojiaosan.user.service.impl;

import com.miaojiaosan.user.dal.repository.database.UserDataBaseRepository;
import com.miaojiaosan.user.dal.repository.redis.UserRedisRepository;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.LoginEvent;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.service.UserOptService;
import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import org.dozer.Mapper;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
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
  private ApplicationEventPublisher eventPublisher;
  @Resource(type = UserDataBaseRepository.class)
  private UserDataBaseRepository dataBase;
  @Resource(type = UserRedisRepository.class)
  private UserRedisRepository redis;
  @Resource
  private Mapper mapper;



  @Override
  public Boolean registry(RegistryDTO registryDTO) {
    Account account = mapper.map(registryDTO,Account.class);
    UserDO userDO = new UserDO();
    userDO.setEmail(account.getEmail());
    userDO.setPhone(account.getPhone());
    userDO.setAccount(account);
    userDO.registry();
    return dataBase.addDataBase(userDO) && publishEvent(new RegistryEvent(userDO));

  }

  @Override
  public Boolean login(LoginDTO loginDTO) {
    Account account = mapper.map(loginDTO,Account.class);
    UserDO userDO = dataBase.byAccountDataBase(account);
    userDO.getAccount().setToken(account.getToken());
    return userDO.login(account)
      && redis.addRedis(userDO)
      && publishEvent(new LoginEvent(userDO));

  }

  private boolean publishEvent(ApplicationEvent applicationEvent){
    eventPublisher.publishEvent(applicationEvent);
    return true;
  }
}
