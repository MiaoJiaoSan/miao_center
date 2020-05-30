package com.miaojiaosan.material.service;

import com.miaojiaosan.material.service.dto.ReleaseDTO;

/**
 * 素材
 * @author miaojiaosan
 */
public interface MaterialOptService {

  /**
   * 发布素材
   * @param releaseDTO {@link ReleaseDTO}
   */
  Boolean release(ReleaseDTO releaseDTO);
}
