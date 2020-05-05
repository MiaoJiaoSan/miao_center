package com.miaojiaosan.user.handler;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.event.LoginEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@Component
public class LoginListener implements ApplicationListener<LoginEvent> {



  @Override
  public void onApplicationEvent(LoginEvent event) {
    UserDO userDO = (UserDO) event.getSource();

  }
}
