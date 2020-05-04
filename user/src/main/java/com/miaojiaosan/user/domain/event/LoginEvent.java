package com.miaojiaosan.user.domain.event;

import com.miaojiaosan.user.domain.UserDO;
import org.springframework.context.ApplicationEvent;

/**
 * 登录事件
 * @author miaojiaosan
 * @date 2020/4/27
 */
public class LoginEvent extends ApplicationEvent {

  public LoginEvent(UserDO userDO) {
    super(userDO);
  }
}
