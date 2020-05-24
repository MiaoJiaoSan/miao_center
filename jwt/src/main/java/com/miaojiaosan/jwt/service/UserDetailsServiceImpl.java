package com.miaojiaosan.jwt.service;

import com.miaojiaosan.jwt.dao.Account;
import com.miaojiaosan.jwt.dao.Role;
import com.miaojiaosan.user.dal.dao.RoleRelDAO;
import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import com.miaojiaosan.user.dal.dao.UserRoleDAO;
import com.miaojiaosan.user.dal.mapperex.RoleRelMapperEx;
import com.miaojiaosan.user.dal.mapperex.UserAccountMapperEx;
import com.miaojiaosan.user.dal.mapperex.UserRoleMapperEx;
import org.dozer.Mapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author miaojiaosan
 */
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserAccountMapperEx userAccountMapperEx;

    @Resource
    private RoleRelMapperEx roleRelMapperEx;

    @Resource
    private UserRoleMapperEx userRoleMapperEx;

    @Resource
    private Mapper mapper;

    /**
     *
     * @param accountStr  账号
     * @return 返回账号信息
     * @throws UsernameNotFoundException 异常
     */
    @Override
    public UserDetails loadUserByUsername(String accountStr) throws UsernameNotFoundException {
        UserAccountDAO userAccountDAO = userAccountMapperEx.byAccount(accountStr);
        List<RoleRelDAO> roleRelLst = roleRelMapperEx.byAccountId(userAccountDAO.getId());
        List<Long> ids = roleRelLst.stream().map(RoleRelDAO::getRoleId).collect(Collectors.toList());
        List<UserRoleDAO> userRoleLst = userRoleMapperEx.byIds(ids);
        Account account = mapper.map(userAccountDAO, Account.class);
        account.setRoles(
            userRoleLst.stream().map(role -> mapper.map(role, Role.class)).collect(Collectors.toList())
        );
        return account;
    }
}
