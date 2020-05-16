package com.miaojiaosan.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@SpringBootConfiguration
public class JackJsonConfiguration {

  @Bean
  public ObjectMapper objectMapper(){
    return new ObjectMapper();
  }
}
