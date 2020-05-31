package com.miaojiaosan.user.handler;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.LoginEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@Component
public class LoginListener implements ApplicationListener<LoginEvent> {

  @Resource
  private HttpServletResponse httpServletResponse;

  @Override
  public void onApplicationEvent(LoginEvent event) {
    UserDO userDO = (UserDO) event.getSource();
    Account account = userDO.getAccount();
    httpServletResponse.addHeader("Authorization", account.getAccessToken());
  }
}
