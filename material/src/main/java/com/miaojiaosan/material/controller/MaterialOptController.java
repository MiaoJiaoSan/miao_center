package com.miaojiaosan.material.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.material.cmd.opt.RecycleOpt;
import com.miaojiaosan.material.cmd.opt.ReleaseOpt;
import com.miaojiaosan.material.service.MaterialOptService;
import com.miaojiaosan.utils.AccountUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@RestController
@RequestMapping("/material/opt")
public class MaterialOptController {
  @Resource
  private MaterialOptService materialOptService;
  @Resource
  private HttpServletRequest httpServletRequest;
  /**
   * 发布素材
   * @return value==true 成功
  */
  @PostMapping("/release")
  public Result<Long> release(@RequestBody ReleaseOpt opt) {
    opt.setAccountId(AccountUtil.id(httpServletRequest));
    return Result.successful(materialOptService.release(opt));
  }

  @DeleteMapping("/recycle")
  public Result<Boolean> recycle(@RequestBody RecycleOpt opt){
    opt.setAccountId(AccountUtil.id(httpServletRequest));
    return Result.successful(materialOptService.recycle(opt));
  }


}
