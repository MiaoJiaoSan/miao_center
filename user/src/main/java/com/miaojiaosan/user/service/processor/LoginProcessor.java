package com.miaojiaosan.user.service.processor;

import com.miaojiaosan.common.dto.Token;
import com.miaojiaosan.user.domain.UserDO;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.event.LoginEvent;
import com.miaojiaosan.user.repository.UserRepository;
import com.miaojiaosan.user.service.dto.LoginDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 *
 * @author miaojiaosan
 * @date 2020/05/15
 */
@Component
public class LoginProcessor {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserRepository userRepository;

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Resource
    private Mapper mapper;
    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly =  true, rollbackFor = Exception.class)
    public UserDO prepare(LoginDTO loginDTO){
        Account account = mapper.map(loginDTO, Account.class);
        UserDO userDO = userRepository.byAccount(account);
        userDO.getAccount().setPassword(loginDTO.getPassword());
        return userDO;
    }

    public void process(UserDO userDO){
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
        assert token != null;
        account.setRefreshToken(token.getRefresh_token());
        userRepository.refreshToken(userDO);
        account.setAccessToken(token.getToken_type() + " " + token.getAccess_token());
    }

    public void completable(UserDO userDO){
        eventPublisher.publishEvent(new LoginEvent(userDO));
    }
}
