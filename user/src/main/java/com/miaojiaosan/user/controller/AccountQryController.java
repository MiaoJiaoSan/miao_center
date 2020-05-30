package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.api.AccountQryApi;
import com.miaojiaosan.user.service.AccountQryService;
import com.miaojiaosan.user.service.dto.AccountDTO;
import com.miaojiaosan.user.utils.AccountUtil;
import com.miaojiaosan.user.vo.AccountVO;
import org.dozer.Mapper;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

  @Resource
  private HttpServletRequest httpServletRequest;

  
  @GetMapping("/id/{id}")
  @Override
  public Result<AccountVO> byId(@PathVariable("id") Long id){
    Long accountId = AccountUtil.id(httpServletRequest);
    AccountDTO accountDTO =accountQryService.byId(accountId);
    AccountVO accountVO = mapper.map(accountDTO, AccountVO.class);
    return Result.successful(accountVO);
  }
}
