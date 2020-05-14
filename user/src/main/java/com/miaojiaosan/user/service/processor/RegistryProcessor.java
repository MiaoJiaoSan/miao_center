package com.miaojiaosan.user.service.processor;

import com.miaojiaosan.user.dal.repository.database.UserDataBaseRepository;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import org.dozer.Mapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *
 * @author miaojiaosan
 * @date 2020/05/14
 */
@Component
public class RegistryProcessor {

    @Resource(type = UserDataBaseRepository.class)
    private UserDataBaseRepository db;

    @Resource
    private Mapper mapper;
    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly =  true, rollbackFor = Exception.class)
    public UserDO prepare(RegistryDTO registryDTO){
        Account account = mapper.map(registryDTO,Account.class);
        UserDO userDO = new UserDO();
        userDO.setEmail(account.getEmail());
        userDO.setPhone(account.getPhone());
        userDO.setAccount(account);
        return userDO;
    }

    @Transactional(rollbackFor =  Exception.class)
    public Boolean process(UserDO userDO){
        return db.addDataBase(userDO);
    }

    public void completable(UserDO userDO){
        eventPublisher.publishEvent(new RegistryEvent(userDO));
    }
}
