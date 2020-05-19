package com.miaojiaosan.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 获取用户信息
 */
@RestController
@RequestMapping("/security")
public class SecurityController {

  @GetMapping("/check")
  public Principal getUser(Principal principal){
    return principal;
  }
}
