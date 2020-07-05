package com.miaojiaosan.user.handler;

import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 用户注册时间监听
 *
 * @author miaojiaosan
 * @date 2020/4/27
 */
@Component
public class RegistryListener implements ApplicationListener<RegistryEvent> {



  @Override
  public void onApplicationEvent(RegistryEvent event) {
    UserDO userDO = (UserDO) event.getSource();
    Account account = userDO.getAccount();
    LoginOpt opt = new LoginOpt();
    opt.setAccount(account.getAccount());
    opt.setPassword(account.getPassword());
    userDO.login(opt);
  }
}
