package com.miaojiaosan.user.handler;

import com.miaojiaosan.common.dto.Token;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.RegistryEvent;
import com.miaojiaosan.user.repository.UserRepository;
import com.miaojiaosan.user.service.UserOptService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 用户注册时间监听
 *
 * @author miaojiaosan
 * @date 2020/4/27
 */
@Component
public class RegistryListener implements ApplicationListener<RegistryEvent> {

  @Resource
  private RestTemplate restTemplate;

  @Value("${security.oauth2.client.access-token-uri}")
  private String accessTokenUri;

  @Resource
  private UserRepository userRepository;

  @Override
  public void onApplicationEvent(RegistryEvent event) {
    UserDO userDO = (UserDO) event.getSource();
    Account account = userDO.getAccount();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth("web", "miaojiaosan");
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "password");
    params.add("scope", "all");
    params.add("username", account.getAccount());
    params.add("password", account.getPassword());
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
    ResponseEntity<Token> exchange = restTemplate.exchange(accessTokenUri, HttpMethod.POST, entity, Token.class);
    Token token = exchange.getBody();
    account.setRefreshToken(token.getRefresh_token());
    userRepository.refreshToken(userDO);
    account.setAccessToken(token.getToken_type() + " " + token.getAccess_token());
  }
}
