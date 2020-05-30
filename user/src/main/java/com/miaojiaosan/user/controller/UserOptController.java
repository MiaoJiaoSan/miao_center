package com.miaojiaosan.user.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.cmd.opt.PersonChangeOpt;
import com.miaojiaosan.user.service.UserOptService;
import com.miaojiaosan.user.service.dto.PersonChangeDTO;
import com.miaojiaosan.user.utils.AccountUtil;
import org.dozer.Mapper;
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
  private Mapper mapper;

  @Resource
  private HttpServletRequest httpServletRequest;

  @PatchMapping("/change")
  public Result<Boolean> change(@RequestBody PersonChangeOpt personChangeOpt){
    Long id = AccountUtil.id(httpServletRequest);
    PersonChangeDTO person = mapper.map(personChangeOpt, PersonChangeDTO.class);
    person.setId(id);
    return Result.successful(userOptService.change(person));
  }

  

}
