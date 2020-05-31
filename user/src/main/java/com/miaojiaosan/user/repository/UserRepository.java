package com.miaojiaosan.user.repository;

import com.miaojiaosan.generate.IdGenerate;
import com.miaojiaosan.user.dal.dao.RoleRelDAO;
import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import com.miaojiaosan.user.dal.dao.UserPersonDAO;
import com.miaojiaosan.user.dal.mapperex.RoleRelMapperEx;
import com.miaojiaosan.user.dal.mapperex.UserAccountMapperEx;
import com.miaojiaosan.user.dal.mapperex.UserPersonMapperEx;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.data.Role;
import org.dozer.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Repository
public class UserRepository {

  @Resource
  private UserAccountMapperEx userAccountMapperEx;
  @Resource
  private UserPersonMapperEx userPersonMapperEx;
  @Resource
  private RoleRelMapperEx roleRelMapperEx;
  @Resource
  private IdGenerate idGenerate;
  @Resource
  private Mapper mapper;



  @Transactional(rollbackFor = Exception.class)
  public void refreshToken(UserDO userDO){
    UserAccountDAO accountDAO = mapper.map(userDO.getAccount(), UserAccountDAO.class);
    accountDAO.setModify(accountDAO.getId());
    userAccountMapperEx.refreshToken(accountDAO);
  }


  @Transactional(rollbackFor = Exception.class)
  public Boolean add(UserDO userDO) {
    //转DO
    UserPersonDAO userDAO = mapper.map(userDO, UserPersonDAO.class);
    Account account = userDO.getAccount();
    UserAccountDAO accountDAO = mapper.map(account, UserAccountDAO.class);
    //组装数据关系
    Long accountId = accountDAO.getId();
    accountDAO.setUserId(userDAO.getId());
    accountDAO.setModify(accountId);
    userDAO.setModify(accountId);
    List<Role> roles = userDO.getRoles();
    List<RoleRelDAO> roleRelLst = roles.stream().map(role -> {
      RoleRelDAO roleRelDAO = new RoleRelDAO();
      roleRelDAO.setId(idGenerate.nextId());
      roleRelDAO.setAccountId(accountId);
      roleRelDAO.setRoleId(role.getId());
      roleRelDAO.setModify(accountId);
      return roleRelDAO;
    }).collect(Collectors.toList());
    //持久化
    if(userAccountMapperEx.insert(accountDAO) == 1){
      userPersonMapperEx.insert(userDAO);
      roleRelMapperEx.batchInsert(roleRelLst);
      return true;
    }
    return false;
  }

  @Transactional(rollbackFor = Exception.class)
  public void modifyUser(UserDO userDO){
    UserPersonDAO userPersonDAO = mapper.map(userDO, UserPersonDAO.class);
    userPersonDAO.setModify(userDO.getAccount().getId());
    userPersonMapperEx.modify(userPersonDAO);
  }

  @Transactional(rollbackFor = Exception.class)
  public void modifyAccount(UserDO userDO){
    Account account = userDO.getAccount();
    UserAccountDAO userAccountDAO = mapper.map(account, UserAccountDAO.class);
    userAccountDAO.setModify(userAccountDAO.getId());
    userAccountMapperEx.modify(userAccountDAO);
  }


  public void byAccount(String account, UserDO userDO){
    UserAccountDAO userAccountDAO = userAccountMapperEx.byAccount(account);
    Account accountValue = mapper.map(userAccountDAO, Account.class);
    userDO.setAccount(accountValue);
  }

  public void byId(Long id, UserDO userDO){
    UserAccountDAO userAccountDAO = userAccountMapperEx.selectByPrimaryKey(id);
    Account account = mapper.map(userAccountDAO, Account.class);
    userDO.setAccount(account);
    userDO.setId(userAccountDAO.getUserId());
  }



}