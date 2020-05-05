package com.miaojiaosan.matereial;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@EnableEurekaClient
@SpringCloudApplication
public class MaterialApplication {

  public static void main(String[] args) {
    SpringApplication.run(MaterialApplication.class, args);
  }
}
