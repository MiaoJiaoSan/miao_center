package com.miaojiaosan.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 * http://demo:demo@localhost:7090/actuator/hystrix.stream demo
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DashBoardApplication {

  public static void main(String[] args) {
    SpringApplication.run(DashBoardApplication.class, args);
  }
}
