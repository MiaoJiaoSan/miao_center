package com.miaojiaosan.jwt;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.miaojiaosan.user.dal")
public class JWTApplication {
  public static void main(String[] args) {
    SpringApplication.run(JWTApplication.class, args);
  }
}
