package com.miaojiaosan.user.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 */
@SpringBootConfiguration
@EnableResourceServer
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {


  @Resource
  private TokenStore tokenStore;
  /**
   * 需要拦截的路径
   * @param http {@link HttpSecurity}
   * @throws Exception 异常
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        //放行
        .antMatchers("/actuator/**","/account/opt/**").permitAll()
        //鉴权
        .antMatchers("/**").authenticated();
  }


  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.tokenStore(tokenStore);
//    resources.authenticationEntryPoint(new RefreshTokenAuthenticationEntryPoint());

  }
}
