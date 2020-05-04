package com.miaojiaosan.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 文章启动
 * @author: miaojiaosan
 * @date: 2020/5/4
 */
@SpringBootApplication
@EnableEurekaClient
public class ArticleApplication {
  public static void main(String[] args) {
    SpringApplication.run(ArticleApplication.class, args);
  }
}
