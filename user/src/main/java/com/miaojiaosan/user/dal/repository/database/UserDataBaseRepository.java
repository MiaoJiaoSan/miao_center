package com.miaojiaosan.user.dal.repository.database;

import com.miaojiaosan.common.enums.RoleCode;
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
import org.dozer.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Repository
public class UserDataBaseRepository {

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

  public Boolean addDataBase(UserDO userDO) {
    UserPersonDAO userPersonDAO = mapper.map(userDO, UserPersonDAO.class);
    int row = userPersonMapperEx.insert(userPersonDAO);
    Long id = userPersonDAO.getId();
    Account account = userDO.getAccount();
    //账号
    UserAccountDAO userAccountDAO = mapper.map(account, UserAccountDAO.class);
    userAccountDAO.setUserId(id);
    userAccountDAO.setModify(id);
    row &= userAccountMapperEx.insert(userAccountDAO);
    //角色关系
    List<Role> roles = userDO.getRoles();
    RoleRelDAO roleRelDAO = new RoleRelDAO();
    roleRelDAO.setModify(id);
    roleRelDAO.setUserId(id);
    roleRelDAO.setRoleId(roles.get(0).getId());
    return row > 0;
  }


  public UserDO byAccount(Account account) {
    UserAccountDAO userAccountDAO = userAccountMapperEx.byAccount(account.getAccount());
    UserPersonDAO userPersonDAO = userPersonMapperEx.selectByPrimaryKey(userAccountDAO.getUserId());
    List<RoleRelDAO> roleRelDAOList = roleRelMapperEx.byUserId(userAccountDAO.getUserId());
    List<Long> roleIds = roleRelDAOList.stream().map(RoleRelDAO::getRoleId).collect(Collectors.toList());
    List<UserRoleDAO> userRoleDAOList = userRoleMapperEx.byIds(roleIds);
    UserDO userDO = mapper.map(userPersonDAO,UserDO.class);
    account = mapper.map(userAccountDAO, Account.class);
    List<Role> roles = new ArrayList<>(userRoleDAOList.size());
    userRoleDAOList.forEach(dao -> roles.add( mapper.map(dao, Role.class)));
    userDO.setAccount(account);
    userDO.setRoles(roles);
    return userDO;
  }

  public Role normal(){
    UserRoleDAO roleRelDAO = userRoleMapperEx.byCode(RoleCode.NORMAL.name());
    return mapper.map(roleRelDAO,Role.class);
  }

}