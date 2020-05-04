package com.miaojiaosan.user.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@SpringBootConfiguration
@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //使用Security需要关闭csrf
    http.csrf().disable();
    super.configure(http);
  }
}
