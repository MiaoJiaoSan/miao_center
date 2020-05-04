package com.miaojiaosan.duplicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author miaojiaosan
 * @date: 2020/4/25
 */

@SpringBootApplication
@EnableEurekaServer
public class DuplicateApplication {

  public static void main(String[] args) {
    SpringApplication.run(DuplicateApplication.class,args);
  }
}

