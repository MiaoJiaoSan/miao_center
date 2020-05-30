package com.miaojiaosan.user.service.processor;

import com.miaojiaosan.processor.Processor;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.repository.UserRepository;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @author miaojiaosan
 * @date 2020/05/14
 */
@Component
public class RegistryProcessor implements Processor<RegistryDTO, UserDO> {

    @Resource
    private UserRepository repository;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Override
    public UserDO prepare(RegistryDTO registryDTO){
        return repository.create(registryDTO);
    }

    @Override
    public void process(UserDO userDO){
        repository.add(userDO);
    }

    @Override
    public void completable(UserDO userDO){
        eventPublisher.publishEvent(new RegistryEvent(userDO));
    }
}
