package com.miaojiaosan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@SpringBootApplication
@EnableEurekaClient
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class,args);
  }
}
