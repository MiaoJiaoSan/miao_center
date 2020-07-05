package com.miaojiaosan.user.service.impl;

import com.miaojiaosan.user.cmd.opt.PersonChangeOpt;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.repository.UserRepository;
import com.miaojiaosan.user.service.UserOptService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

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
  private ApplicationContext applicationContext;
  @Resource
  private ApplicationEventPublisher eventPublisher;
  @Resource
  private UserRepository userRepository;

  @Override
  public Boolean change(@RequestBody @Validated PersonChangeOpt opt) {
    UserDO userDO = applicationContext.getBean(UserDO.class);
    userRepository.accountById(opt.getAccountId(), userDO);
    userDO.change(opt);
    //TODO 领域事件
    return true;
  }



}
