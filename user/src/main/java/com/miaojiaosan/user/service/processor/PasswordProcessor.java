package com.miaojiaosan.user.service.processor;

import com.miaojiaosan.processor.Processor;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.repository.UserRepository;
import com.miaojiaosan.user.service.dto.PasswordDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 */
@Component
public class PasswordProcessor implements Processor<PasswordDTO, UserDO> {

  @Resource
  private UserRepository userRepository;

  @Resource
  private Mapper mapper;

  @Override
  public UserDO prepare(PasswordDTO passwordDTO){
    Account account = mapper.map(passwordDTO, Account.class);
    return userRepository.byId(account);
  }

  @Override
  public void process(UserDO userDO){
    userRepository.modify(userDO);
  }

  @Override
  public void completable(UserDO userDO){
  }
}
