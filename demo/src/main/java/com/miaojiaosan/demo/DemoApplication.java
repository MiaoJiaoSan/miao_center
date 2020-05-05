package com.miaojiaosan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@SpringCloudApplication
@EnableEurekaClient
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class,args);
  }
}
