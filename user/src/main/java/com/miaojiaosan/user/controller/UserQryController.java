package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.api.UserQryApi;
import com.miaojiaosan.user.vo.UserVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户查询controller
 * @author miaojiaosan
 * @date 2020/4/25
 */
@RestController
@RequestMapping("/user/qry")
public class UserQryController implements UserQryApi {

  @HystrixCommand
  @GetMapping("/id/{id}")
  @Override
  public Result<UserVO> byId(@PathVariable("id") Long id){
    return Result.empty();
  }

  @HystrixCommand
  @GetMapping("/account/{account}")
  @Override
  public Result<UserVO> byAccount(@PathVariable("account") String account){
    return Result.empty();
  }

}
