package com.miaojiaosan.user.service.processor;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.repository.UserRepository;
import com.miaojiaosan.user.service.dto.PersonChangeDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 */
@Component
public class PersonChangeProcessor {

  @Resource
  private UserRepository userRepository;

  public UserDO prepare(PersonChangeDTO personChangeDTO){
    Account account = new Account();
    account.setAccount(personChangeDTO.getAccount());
    return userRepository.byAccount(account);
  }

  public void process(UserDO userDO){
    userRepository.modify(userDO);
  }

  public void completable(UserDO userDO){
  }
}
