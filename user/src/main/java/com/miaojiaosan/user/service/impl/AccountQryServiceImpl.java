package com.miaojiaosan.user.service.impl;

import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import com.miaojiaosan.user.dal.mapperex.UserAccountMapperEx;
import com.miaojiaosan.user.service.AccountQryService;
import com.miaojiaosan.user.service.dto.AccountDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Service
public class AccountQryServiceImpl implements AccountQryService {
  
  @Resource
  private UserAccountMapperEx userAccountMapperEx;

  @Resource
  private Mapper mapper;
  
  @Override
  public AccountDTO byId(Long id) {
    UserAccountDAO userAccountDAO = userAccountMapperEx.selectByPrimaryKey(id);
    return mapper.map(userAccountDAO, AccountDTO.class);
  }

  @Override
  public AccountDTO byAccount(String account) {
    UserAccountDAO userAccountDAO = userAccountMapperEx.byAccount(account);
    return mapper.map(userAccountDAO, AccountDTO.class);
  }

  @Override
  public AccountDTO byEmail(String email) {
    throw new UnsupportedOperationException("方法暂未实现");
  }

  @Override
  public AccountDTO byPhone(String phone) {
    throw new UnsupportedOperationException("方法暂未实现");
  }
}
