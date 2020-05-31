package com.miaojiaosan.material.service.impl;

import com.miaojiaosan.material.cmd.opt.RecycleOpt;
import com.miaojiaosan.material.cmd.opt.ReleaseOpt;
import com.miaojiaosan.material.domain.MaterialDO;
import com.miaojiaosan.material.repository.MaterialRepository;
import com.miaojiaosan.material.service.MaterialOptService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author miaojiaosan
 */
@Service
public class MaterialOptServiceImpl implements MaterialOptService {

  @Resource
  private ApplicationContext applicationContext;
  @Resource
  private ApplicationEventPublisher eventPublisher;
  @Resource
  private MaterialRepository materialRepository;


  @Override
  public Long release(ReleaseOpt opt) {
    //创建
    MaterialDO materialDO = applicationContext.getBean(MaterialDO.class);
    //加载
    materialRepository.byId(opt.getId(), materialDO);
    //发布素材
    materialDO.release(opt);
    //TODO 领域事件
    //返回 素材id
    return materialDO.getId();
  }



  @Override
  public Boolean recycle(RecycleOpt opt) {
    MaterialDO materialDO = applicationContext.getBean(MaterialDO.class);
    //加载
    materialRepository.byId(opt.getId(), materialDO);
    //移除
    materialDO.recycle(opt.getAccountId());
    //TODO 领域事件
    //返回删除成功
    return true;
  }


}
