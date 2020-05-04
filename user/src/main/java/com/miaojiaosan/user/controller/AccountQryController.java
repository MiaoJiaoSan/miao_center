package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.api.AccountQryApi;
import com.miaojiaosan.user.service.AccountQryService;
import com.miaojiaosan.user.service.dto.AccountDTO;
import com.miaojiaosan.user.vo.AccountVO;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户账号查询
 * @author miaojiaosan
 * @date 2020/4/25
 */
@RestController
@RequestMapping("/account/qry")
public class AccountQryController implements AccountQryApi {

  @Resource
  private AccountQryService accountQryService;

  @Resource
  private Mapper mapper;

  @Override
  @GetMapping("/id/{id}")
  public Result<AccountVO> byId(@PathVariable("id") Long id){
    AccountDTO accountDTO =accountQryService.byId(id);
    AccountVO accountVO = mapper.map(accountDTO, AccountVO.class);
    return Result.successful(accountVO);
  }

  @GetMapping("/account/{account}")
  @Override
  public Result<AccountVO> byAccount(@PathVariable("account") String account){
    AccountDTO accountDTO = accountQryService.byAccount(account);
    AccountVO accountVO = mapper.map(accountDTO, AccountVO.class);
    return Result.successful(accountVO);
  }

  @GetMapping("/email/{email}")
  @Override
  public Result<AccountVO> byEmail(@PathVariable("email") String email) {
    AccountDTO accountDTO =accountQryService.byEmail(email);
    AccountVO accountVO = mapper.map(accountDTO, AccountVO.class);
    return Result.successful(accountVO);
  }

  @GetMapping("/phone/{phone}")
  @Override
  public Result<AccountVO> byPhone(@PathVariable("phone") String phone) {
    AccountDTO accountDTO = accountQryService.byPhone(phone);
    AccountVO accountVO = mapper.map(accountDTO, AccountVO.class);
    return Result.successful(accountVO);
  }

}
