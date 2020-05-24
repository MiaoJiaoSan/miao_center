package com.miaojiaosan.user.repository;

import com.miaojiaosan.user.dal.dao.RoleRelDAO;
import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import com.miaojiaosan.user.dal.dao.UserPersonDAO;
import com.miaojiaosan.user.dal.dao.UserRoleDAO;
import com.miaojiaosan.user.dal.mapperex.RoleRelMapperEx;
import com.miaojiaosan.user.dal.mapperex.UserAccountMapperEx;
import com.miaojiaosan.user.dal.mapperex.UserPersonMapperEx;
import com.miaojiaosan.user.dal.mapperex.UserRoleMapperEx;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.data.Role;
import com.miaojiaosan.user.service.dto.AccountDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import org.dozer.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
  private UserRoleMapperEx userRoleMapperEx;
  @Resource
  private RoleRelMapperEx roleRelMapperEx;

  @Resource
  private Mapper mapper;

  @Transactional(readOnly =  true, rollbackFor = Exception.class)
  public UserDO create(RegistryDTO registryDTO){
    Account account = mapper.map(registryDTO, Account.class);
    account.setRefreshToken("");
    UserRoleDAO normal = userRoleMapperEx.byCode("NORMAL");
    UserDO userDO = new UserDO();
    userDO.setAccount(account);
    userDO.setEmail(account.getEmail());
    userDO.setPhone(account.getPhone());
    List<UserRoleDAO> userRoleLst = Collections.singletonList(normal);
    List<Role> roles = userRoleLst.stream()
        .map(role -> mapper.map(role, Role.class)).collect(Collectors.toList());
    userDO.setRoles(roles);
    return userDO;
  }


  @Transactional(rollbackFor = Exception.class)
  public void add(UserDO userDO) {
    UserPersonDAO user = mapper.map(userDO, UserPersonDAO.class);
    userPersonMapperEx.insert(user);
    Long userId = user.getId();
    userDO.setId(userId);
    Account account = userDO.getAccount();
    account.setModify(userId);
    UserAccountDAO accountDAO = mapper.map(account, UserAccountDAO.class);
    accountDAO.setPassword("{bcrypt}"+ new BCryptPasswordEncoder().encode(account.getPassword()));
    accountDAO.setUserId(userId);
    accountDAO.setModify(userId);
    accountDAO.setEmail(user.getEmail());
    accountDAO.setPhone(user.getPhone());
    userAccountMapperEx.insert(accountDAO);
    Long accountId = accountDAO.getId();
    Assert.notNull(accountId,"账号已被注册");
    account.setId(accountId);
    List<Role> roles = userDO.getRoles();
    List<RoleRelDAO> roleRelLst = roles.stream().map(role -> {
      RoleRelDAO roleRelDAO = new RoleRelDAO();
      roleRelDAO.setAccountId(accountId);
      roleRelDAO.setRoleId(role.getId());
      roleRelDAO.setModify(accountId);
      return roleRelDAO;
    }).collect(Collectors.toList());
    roleRelMapperEx.batchInsert(roleRelLst);
  }

  @Transactional(rollbackFor = Exception.class)
  public void refreshToken(UserDO userDO){
    UserAccountDAO account = mapper.map(userDO.getAccount(), UserAccountDAO.class);
    userAccountMapperEx.refreshToken(account);
  }


  public UserDO byAccount(Account account) {

    return null;
  }



}