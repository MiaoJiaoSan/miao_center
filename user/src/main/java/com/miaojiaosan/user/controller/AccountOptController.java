package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.PasswordOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;
import com.miaojiaosan.user.service.AccountOptService;
import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import com.miaojiaosan.user.vo.AccountVO;
import com.miaojiaosan.utils.AccountUtil;
import org.dozer.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/account/opt")
public class AccountOptController {

  @Resource
  private AccountOptService accountOptService;

  @Resource
  private Mapper mapper;

  @Resource
  private HttpServletRequest httpServletRequest;


  @PostMapping("/registry")
  public Result<AccountVO> registry(@RequestBody @Validated RegistryOpt opt) {
    RegistryDTO dto = accountOptService.registry(opt);
    AccountVO vo = mapper.map(dto, AccountVO.class);
    return Result.successful(vo);
  }

  @PatchMapping("/login")
  public Result<AccountVO> login(@RequestBody @Validated LoginOpt opt) {
    LoginDTO dto = accountOptService.login(opt);
    AccountVO vo = mapper.map(dto, AccountVO.class);
    return Result.successful(vo);
  }

  @PatchMapping("/password")
  public Result<Boolean> password(@RequestBody @Validated PasswordOpt opt){
    opt.setId(AccountUtil.id(httpServletRequest));
    return Result.successful(accountOptService.password(opt));
  }
}
