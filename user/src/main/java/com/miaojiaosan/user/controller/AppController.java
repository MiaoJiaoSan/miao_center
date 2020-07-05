package com.miaojiaosan.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Scope("refresh")
public class AppController {

  @Value("${spring.application.name}")
  private String applicationName;


  @GetMapping("/application/name")
  public String applicationName(){
    return applicationName;
  }


}
