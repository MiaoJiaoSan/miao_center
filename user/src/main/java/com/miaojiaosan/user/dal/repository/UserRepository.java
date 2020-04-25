package com.miaojiaosan.user.dal.repository;

import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import com.miaojiaosan.user.dal.dao.UserPersonDAO;
import com.miaojiaosan.user.dal.mapperex.UserAccountMapperEx;
import com.miaojiaosan.user.dal.mapperex.UserPersonMapperEx;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 * @date: 2020/4/25
 */
@Repository
public class UserRepository {

  @Resource
  private UserAccountMapperEx userAccountMapperEx;
  @Resource
  private UserPersonMapperEx userPersonMapperEx;

  @Transactional
  public Boolean addDataBase(UserDO userDO){
    UserPersonDAO userPersonDAO = new UserPersonDAO();
    BeanUtils.copyProperties(userDO, userPersonDAO);
    int row = userPersonMapperEx.insert(userPersonDAO);
    if(row >= 0) {
      Account account = userDO.getAccount();
      UserAccountDAO userAccountDAO = new UserAccountDAO();
      BeanUtils.copyProperties(account, userAccountDAO);
      userAccountDAO.setUserId(userPersonDAO.getId());
      userAccountDAO.setModify(userPersonDAO.getId());
      row &= userAccountMapperEx.insert(userAccountDAO);
    }
    return row > 0;
  }

  public Boolean addRedis(UserDO userDO){
    return true;
  }

  public UserDO byAccountDataBase(Account account){
    UserAccountDAO userAccountDAO = userAccountMapperEx.byAccount(account.getAccount());
    UserPersonDAO userPersonDAO = userPersonMapperEx.selectByPrimaryKey(userAccountDAO.getUserId());
    UserDO userDO = new UserDO();
    BeanUtils.copyProperties(userPersonDAO, userDO);
    account = new Account();
    BeanUtils.copyProperties(userAccountDAO, account);
    userDO.setAccount(account);
    return userDO;
  }


}