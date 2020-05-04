package com.miaojiaosan.demo.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@SpringBootConfiguration
public class DemoConfiguration {

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
