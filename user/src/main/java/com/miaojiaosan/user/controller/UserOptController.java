package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.cmd.opt.PersonChangeOpt;
import com.miaojiaosan.user.service.UserOptService;
import com.miaojiaosan.utils.AccountUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户数据操作controller
 * @author miaojiaosan
 * @date 2020/4/25
 */
@RestController
@RequestMapping("/user/opt")
public class UserOptController {

  @Resource
  private UserOptService userOptService;
  @Resource
  private HttpServletRequest httpServletRequest;

  @PatchMapping("/change")
  public Result<Boolean> change(@RequestBody @Validated PersonChangeOpt opt){
    opt.setAccountId(AccountUtil.id(httpServletRequest));
    return Result.successful(userOptService.change(opt));
  }

  

}
