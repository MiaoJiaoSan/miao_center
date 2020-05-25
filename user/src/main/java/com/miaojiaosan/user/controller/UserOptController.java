package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.api.UserOptApi;
import com.miaojiaosan.user.cmd.opt.PersonChangeOpt;
import com.miaojiaosan.user.service.UserOptService;
import com.miaojiaosan.user.service.dto.PersonChangeDTO;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.PatchMapping;
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

  @PatchMapping("/change")
  public Result<Boolean> change(@RequestBody PersonChangeOpt personChangeOpt){
    PersonChangeDTO person = mapper.map(personChangeOpt, PersonChangeDTO.class);
    return Result.successful(userOptService.change(person));
  }

  

}
