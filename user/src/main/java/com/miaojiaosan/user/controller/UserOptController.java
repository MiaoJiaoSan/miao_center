package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.api.UserOptApi;
import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;
import com.miaojiaosan.user.service.UserOptService;
import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户数据操作controller
 * @author miaojiaosan
 * @date 2020/4/25
 */
@RestController
@RequestMapping("/user/opt")
public class UserOptController implements UserOptApi {

  @Resource
  private UserOptService userOptService;

  @Resource
  private Mapper mapper;

  @PostMapping("/registry")
  @Override
  public Result<Boolean> registry(@RequestBody RegistryOpt registryOpt) {
    Result<Boolean> result = Result.unsuccessful("注册失败");
    RegistryDTO registryDTO = mapper.map(registryOpt, RegistryDTO.class);
    if(userOptService.registry(registryDTO)){
      result = Result.successful(true);
    }
    return result;
  }


  @PostMapping("/login")
  @Override
  public Result<Boolean> login(@RequestBody LoginOpt loginOpt) {
    Result<Boolean> result = Result.unsuccessful("登录失败");
    LoginDTO loginDTO = mapper.map(loginOpt, LoginDTO.class);
    if(userOptService.login(loginDTO)){
      result = Result.successful(true);
    }
    return result;
  }
}
