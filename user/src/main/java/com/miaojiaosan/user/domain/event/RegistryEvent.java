package com.miaojiaosan.user.domain.event;

import com.miaojiaosan.user.domain.UserDO;
import org.springframework.context.ApplicationEvent;

/**
 * 注册事件
 * @author miaojiaosan
 * @date 2020/4/27
 */
public class RegistryEvent extends ApplicationEvent {

  public RegistryEvent(UserDO userDO) {
    super(userDO);
  }

}
