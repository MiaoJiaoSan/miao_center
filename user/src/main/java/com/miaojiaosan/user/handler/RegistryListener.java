package com.miaojiaosan.user.handler;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.service.UserOptService;
import com.miaojiaosan.user.service.dto.LoginDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户注册时间监听
 * @author miaojiaosan
 * @date 2020/4/27
 */
@Component
public class RegistryListener implements ApplicationListener<RegistryEvent> {

  @Resource
  private UserOptService userOptService;

  @Override
  public void onApplicationEvent(RegistryEvent event) {
    UserDO userDO = (UserDO) event.getSource();
    Account account = userDO.getAccount();
    if (account.getValidate() != 1) {
      return;
    }
    LoginDTO dto = new LoginDTO();
    BeanUtils.copyProperties(account, dto);
    userOptService.login(dto);
  }
}
