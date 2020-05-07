package com.miaojiaosan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@SpringCloudApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.miaojiaosan.user.api")
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class,args);
  }
}
