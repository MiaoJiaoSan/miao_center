package com.miaojiaosan.user.service.impl;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.service.UserOptService;
import com.miaojiaosan.user.service.dto.PersonChangeDTO;
import com.miaojiaosan.user.service.processor.PersonChangeProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户数据操作service
 *
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Service
public class UserOptServiceImpl implements UserOptService {

  @Resource
  private PersonChangeProcessor personChangeProcessor;

  @Override
  public Boolean change(PersonChangeDTO personChangeDTO) {
    UserDO userDO = personChangeProcessor.prepare(personChangeDTO);
    userDO.change(personChangeDTO);
    personChangeProcessor.process(userDO);
    personChangeProcessor.completable(userDO);
    return true;
  }
}
