package com.miaojiaosan.user.service.processor;

import com.miaojiaosan.user.dal.repository.database.UserDataBaseRepository;
import com.miaojiaosan.user.dal.repository.redis.UserRedisRepository;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.LoginEvent;
import com.miaojiaosan.user.service.dto.LoginDTO;
import org.dozer.Mapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *
 * @author miaojiaosan
 * @date 2020/05/15
 */
@Component
public class LoginProcessor {

    @Resource(type = UserRedisRepository.class)
    private UserRedisRepository redis;
    @Resource(type = UserDataBaseRepository.class)
    private UserDataBaseRepository db;
    @Resource
    private Mapper mapper;
    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly =  true, rollbackFor = Exception.class)
    public UserDO prepare(LoginDTO loginDTO){
        Account account = mapper.map(loginDTO,Account.class);
        UserDO userDO = db.byAccount(account);
        userDO.getAccount().setToken(account.getToken());
        return userDO;
    }

    public Boolean process(UserDO userDO){
        return redis.addRedis(userDO);
    }

    public void completable(UserDO userDO){
        eventPublisher.publishEvent(new LoginEvent(userDO));
    }
}
