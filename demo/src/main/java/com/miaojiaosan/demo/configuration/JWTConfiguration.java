package com.miaojiaosan.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@SpringBootConfiguration
public class JWTConfiguration {

  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Bean
  @Qualifier("tokenStore")
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter);
  }

  @Bean
  @Primary
  public JwtAccessTokenConverter jwtTokenEnhancer() {
    // 用作JWT转换器
    JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
    Resource resource = new ClassPathResource("public.cert");
    String publicKey;
    try {
      publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    //设置公钥
    converter.setVerifierKey(publicKey);
    return converter;
  }
}
