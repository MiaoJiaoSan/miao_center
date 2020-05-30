package com.miaojiaosan.material.service.impl;

import com.miaojiaosan.material.domain.MaterialDO;
import com.miaojiaosan.material.service.MaterialOptService;
import com.miaojiaosan.material.service.dto.ReleaseDTO;
import com.miaojiaosan.material.service.processor.ReleaseProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author miaojiaosan
 */
@Service
public class MaterialOptServiceImpl implements MaterialOptService {


  @Resource
  private ReleaseProcessor releaseProcessor;
  @Override
  public Boolean release(ReleaseDTO releaseDTO) {
    MaterialDO materialDO = releaseProcessor.prepare(releaseDTO);
    materialDO.release();
    releaseProcessor.process(materialDO);
    releaseProcessor.completable(materialDO);
    return true;
  }

}
