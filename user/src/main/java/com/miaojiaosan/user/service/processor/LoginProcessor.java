package com.miaojiaosan.user.service.processor;

import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.event.LoginEvent;
import com.miaojiaosan.user.repository.UserRepository;
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

    @Resource
    private UserRepository userRepository;

    @Resource
    private Mapper mapper;
    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly =  true, rollbackFor = Exception.class)
    public UserDO prepare(LoginDTO loginDTO){

        return null;
    }

    public Boolean process(UserDO userDO){
        return null;
    }

    public void completable(UserDO userDO){
        eventPublisher.publishEvent(new LoginEvent(userDO));
    }
}
