package com.miaojiaosan.jwt.service;

import com.miaojiaosan.jwt.dao.Account;
import com.miaojiaosan.jwt.dao.Role;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 */
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String USER = "select id,account,password  from user_account where account = ? ";
    public static final String ROLE = "select role.id,role.code,role.name  from role_rel rel inner join user_role as role on rel.role_id = role.id  where rel.account_id = ? ";
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param accountStr  账号
     * @return 返回账号信息
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String accountStr) throws UsernameNotFoundException {
        Account account = jdbcTemplate.queryForObject(USER, new BeanPropertyRowMapper<>(Account.class),accountStr);
        assert account != null;
        account.setRoles(jdbcTemplate.queryForList(ROLE, new Object[]{account.getId()} ,Role.class));
        return account;
    }
}
