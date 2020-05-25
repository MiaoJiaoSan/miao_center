package com.miaojiaosan.user.handler;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.service.processor.LoginProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户注册时间监听
 *
 * @author miaojiaosan
 * @date 2020/4/27
 */
@Component
public class RegistryListener implements ApplicationListener<RegistryEvent> {

  @Resource
  private LoginProcessor loginProcessor;

  @Override
  public void onApplicationEvent(RegistryEvent event) {
    UserDO userDO = (UserDO) event.getSource();
    loginProcessor.process(userDO);
  }
}
