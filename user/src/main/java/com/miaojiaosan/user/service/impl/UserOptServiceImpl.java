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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

  @Resource
  private HttpSession httpSession;


  @Override
  public Boolean registry(RegistryDTO registryDTO) {
    Account account = mapper.map(registryDTO,Account.class);
    UserDO userDO = new UserDO();
    userDO.setEmail(account.getEmail());
    userDO.setPhone(account.getPhone());
    userDO.setAccount(account);
    boolean rst = dataBase.addDataBase(userDO);
    if (rst) {
      userDO.registry();
      eventPublisher.publishEvent(new RegistryEvent(userDO));
    }
    return rst;

  }

  @Override
  public Boolean login(LoginDTO loginDTO) {
    Account account = mapper.map(loginDTO,Account.class);
    UserDO userDO = dataBase.byAccountDataBase(account);
    boolean res = userDO.login(account);
    if (res) {
      userDO.getAccount().setSessionId(httpSession.getId());
      res = redis.addRedis(userDO);
      eventPublisher.publishEvent(new LoginEvent(userDO));
    }
    return res;
  }


}
