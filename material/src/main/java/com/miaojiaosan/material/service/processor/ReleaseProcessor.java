package com.miaojiaosan.material.service.processor;

import com.miaojiaosan.material.domain.MaterialDO;
import com.miaojiaosan.material.repository.MaterialRepository;
import com.miaojiaosan.material.service.dto.ReleaseDTO;
import com.miaojiaosan.processor.Processor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @author miaojiaosan
 */
@Component
public class ReleaseProcessor implements Processor<ReleaseDTO,MaterialDO> {


  @Resource
  private MaterialRepository materialRepository;


  @Override
  public MaterialDO prepare(ReleaseDTO dto){
    return materialRepository.create(dto);
  }

  @Override
  public void process(MaterialDO materialDO){
    materialRepository.add(materialDO);


  }

  @Override
  public void completable(MaterialDO materialDO){

  }

}
