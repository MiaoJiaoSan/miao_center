package com.miaojiaosan.material;

import com.miaojiaosan.generate.IdGenerate;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@EnableEurekaClient
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.miaojiaosan.user.api")
public class MaterialApplication {

  public static void main(String[] args) {
    SpringApplication.run(MaterialApplication.class, args);
  }

  @Bean
  public IdGenerate idGenerate(){
    return new IdGenerate(1,1);
  }
}
