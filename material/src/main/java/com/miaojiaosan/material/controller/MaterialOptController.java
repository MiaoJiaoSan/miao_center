package com.miaojiaosan.material.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.material.api.MaterialOptApi;
import com.miaojiaosan.material.cmd.opt.ReleaseOpt;
import com.miaojiaosan.material.service.MaterialOptService;
import com.miaojiaosan.material.service.dto.ReleaseDTO;
import org.dozer.Mapper;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@RestController
@RequestMapping("/material/opt")
public class MaterialOptController implements MaterialOptApi {


  @Resource
  private Mapper mapper;
  @Resource
  private MaterialOptService materialOptService;

  /**
   * 发布素材
   * @return value==true 成功
  */
  
  @PostMapping("/release")
  @Override
  public Result<Boolean> release(@RequestBody ReleaseOpt releaseOpt) {
    ReleaseDTO dto = mapper.map(releaseOpt, ReleaseDTO.class);
    return Result.successful(materialOptService.release(dto));
  }
}
