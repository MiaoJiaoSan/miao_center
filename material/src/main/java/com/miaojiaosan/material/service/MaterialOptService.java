package com.miaojiaosan.material.service;

import com.miaojiaosan.material.cmd.opt.RecycleOpt;
import com.miaojiaosan.material.cmd.opt.ReleaseOpt;

/**
 * 素材
 * @author miaojiaosan
 */
public interface MaterialOptService {

  /**
   * 发布素材
   * @param opt {@link ReleaseOpt}
   */
  Long release(ReleaseOpt opt);

  /**
   * 删除素材
   * @param opt {@link RecycleOpt}
   */
  Boolean recycle(RecycleOpt opt);
}
