package com.miaojiaosan.user.service.processor;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.repository.UserRepository;
import com.miaojiaosan.user.service.dto.PasswordDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 */
@Component
public class PasswordProcessor {

  @Resource
  private UserRepository userRepository;

  public UserDO prepare(PasswordDTO passwordDTO){
    Account account = new Account();
    account.setId(passwordDTO.getId());
    return userRepository.byId(account);
  }

  public void process(UserDO userDO){
    userRepository.modify(userDO);
  }

  public void completable(UserDO userDO){
  }
}
