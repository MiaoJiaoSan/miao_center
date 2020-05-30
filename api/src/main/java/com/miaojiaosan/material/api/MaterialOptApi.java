package com.miaojiaosan.material.api;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.material.cmd.opt.ReleaseOpt;

/**
 * 素材
 * @author miaojiaosan
 * @date 2020/5/4
 */
public interface MaterialOptApi {

  /**
   * 发布素材
   * @return true = 成功
   */
  Result<Boolean> release(ReleaseOpt releaseOpt);
}
