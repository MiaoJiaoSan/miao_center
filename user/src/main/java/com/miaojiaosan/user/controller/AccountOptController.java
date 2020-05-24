package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.api.AccountOptApi;
import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;
import com.miaojiaosan.user.service.AccountOptService;
import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import com.miaojiaosan.user.vo.AccountVO;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account/opt")
public class AccountOptController implements AccountOptApi {

  @Resource
  private AccountOptService accountOptService;

  @Resource
  private Mapper mapper;


  @PostMapping("/registry")
  @Override
  public Result<AccountVO> registry(@RequestBody RegistryOpt registryOpt) {
    RegistryDTO dto = mapper.map(registryOpt, RegistryDTO.class);
    dto = accountOptService.registry(dto);
    AccountVO vo = mapper.map(dto, AccountVO.class);
    return Result.successful(vo);
  }

  @PatchMapping("/login")
  @Override
  public Result<AccountVO> login(@RequestBody LoginOpt loginOpt) {
    LoginDTO dto = mapper.map(loginOpt, LoginDTO.class);
    dto = accountOptService.login(dto);
    AccountVO vo = mapper.map(dto, AccountVO.class);
    return Result.successful(vo);
  }
}
