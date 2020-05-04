package com.miaojiaosan.demo.controller;

import com.miaojiaosan.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

  @Resource
  private RestTemplate restTemplate;

  @PostMapping("/check")
  public Result<Boolean> release() {
    //url 实例名 + url
    Result<?> voResult = restTemplate.getForObject("http://user:user@user/account/qry/id/4", Result.class);
    System.out.println(voResult);
    return Result.successful(true);
  }

}
