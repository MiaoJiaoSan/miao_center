package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.api.AccountQryApi;
import com.miaojiaosan.user.vo.AccountVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户账号查询
 * @author miaojiaosan
 * @date: 2020/4/25
 */
@RestController
@RequestMapping("/account/qry")
public class AccountQryController implements AccountQryApi {

  @Override
  @GetMapping("/id/{id}")
  public Result<AccountVO> byId(@PathVariable("id") Long id){
    return Result.empty();
  }

  @GetMapping("/account/{account}")
  @Override
  public Result<AccountVO> byAccount(@PathVariable("account") String account){
    return Result.empty();
  }

  @GetMapping("/email/{email}")
  @Override
  public Result<AccountVO> byEmail(@PathVariable("email") String email) {
    return Result.empty();
  }

  @GetMapping("/phone/{phone}")
  @Override
  public Result<AccountVO> byPhone(@PathVariable("phone") String phone) {
    return Result.empty();
  }

}
