package com.miaojiaosan.user.handler;

import com.miaojiaosan.user.domain.event.LoginEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@Component
public class LoginListener implements ApplicationListener<LoginEvent> {

  @Resource
  private RedisTemplate<String, Integer> redisTemplate;

  @Override
  public void onApplicationEvent(LoginEvent event) {
    //TODO
  }
}
