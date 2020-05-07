package com.miaojiaosan.user.dal.repository.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@Repository
public class UserRedisRepository {

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Resource
  private ObjectMapper objectMapper;

  public Boolean addRedis(UserDO userDO) {
    try {
      Account account = userDO.getAccount();
      stringRedisTemplate.opsForValue().set(account.getToken(), objectMapper.writeValueAsString(userDO));
      return true;
    }catch (Exception e){
      return false;
    }
  }
}
