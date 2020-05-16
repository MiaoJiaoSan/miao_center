package com.miaojiaosan.matereial.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.material.api.MaterialOptApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@RestController
@RequestMapping("/material/opt")
public class MaterialOptController implements MaterialOptApi {

  /**
   * 发布素材
   * @return value==true 成功
  */
  
  @PostMapping("/release")
  @Override
  public Result<Boolean> release() {
    return null;
  }
}
