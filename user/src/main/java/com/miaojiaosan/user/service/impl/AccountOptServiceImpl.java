package com.miaojiaosan.user.service.impl;

import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.PasswordOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;
import com.miaojiaosan.user.dal.dao.UserRoleDAO;
import com.miaojiaosan.user.dal.mapperex.UserRoleMapperEx;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Role;
import com.miaojiaosan.user.domain.event.LoginEvent;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.repository.UserRepository;
import com.miaojiaosan.user.service.AccountOptService;
import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountOptServiceImpl implements AccountOptService {

  @Resource
  private ApplicationContext applicationContext;
  @Resource
  private ApplicationEventPublisher eventPublisher;
  @Resource
  private UserRoleMapperEx userRoleMapperEx;
  @Resource
  private UserRepository userRepository;
  @Resource
  private Mapper mapper;

  @Override
  public RegistryDTO registry(RegistryOpt opt) {
    //创建
    UserDO userDO = applicationContext.getBean(UserDO.class);
    //加载
    UserRoleDAO normal = userRoleMapperEx.byCode("NORMAL");
    List<UserRoleDAO> userRoleLst = Collections.singletonList(normal);
    List<Role> roles = userRoleLst.stream()
        .map(role ->  mapper.map(role, Role.class)).collect(Collectors.toList());
    //注册
    userDO.registry(opt,roles);
    //领域事件
    eventPublisher.publishEvent(new RegistryEvent(userDO));
    //返回
    return mapper.map(userDO.getAccount(), RegistryDTO.class);
  }



  @Override
  public LoginDTO login(LoginOpt opt) {
    //创建
    UserDO userDO = applicationContext.getBean(UserDO.class);
    //加载
    userRepository.loadByAccount(opt.getAccount(), userDO);
    //登陆
    userDO.login(opt);
    //领域事件
    eventPublisher.publishEvent(new LoginEvent(userDO));
    //返回
    return mapper.map(userDO.getAccount(), LoginDTO.class);

  }

  @Override
  public Boolean password(PasswordOpt opt) {
    //创建
    UserDO userDO = applicationContext.getBean(UserDO.class);
    //加载
    userRepository.loadById(opt.getId(),userDO);
    //修改密码
    userDO.password(opt);
    //TODO 领域事件
    //返回
    return true;
  }


}
