package com.miaojiaosan.user.service.processor;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.repository.UserRepository;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @author miaojiaosan
 * @date 2020/05/14
 */
@Component
public class RegistryProcessor {

    @Resource
    private UserRepository repository;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    public UserDO prepare(RegistryDTO registryDTO){
        return repository.create(registryDTO);
    }

    public void process(UserDO userDO){
        repository.add(userDO);
    }

    public void completable(UserDO userDO){
        eventPublisher.publishEvent(new RegistryEvent(userDO));
    }
}
