package com.miaojiaosan.material.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.miaojiaosan.material.cmd.qry.MaterialListQry;
import com.miaojiaosan.material.dal.dao.MaterialDAO;
import com.miaojiaosan.material.dal.mapperex.MaterialMapperEx;
import com.miaojiaosan.material.service.MaterialQryService;
import com.miaojiaosan.material.service.dto.MaterialDTO;
import com.miaojiaosan.material.service.dto.MaterialListDTO;
import com.miaojiaosan.material.service.dto.PageDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.miaojiaosan.material.constant.Constant.DRAFT;
import static com.miaojiaosan.material.constant.Constant.RELEASE;

@Service
public class MaterialQryServiceImpl implements MaterialQryService {

  @Resource
  private MaterialMapperEx materialMapperEx;
  @Resource
  private Mapper mapper;

  @Transactional(rollbackFor = Exception.class, readOnly = true)
  @Override
  public PageDTO<MaterialListDTO> draft(MaterialListQry qry) {
    qry.setState(DRAFT);
    return getMaterialList(qry);
  }

  @Transactional(rollbackFor = Exception.class, readOnly = true)
  @Override
  public PageDTO<MaterialListDTO> list(MaterialListQry qry) {
    qry.setState(RELEASE);
    return getMaterialList(qry);
  }

  @Override
  public MaterialDTO byId(Long id) {
    MaterialDAO materialDAO = materialMapperEx.selectByPrimaryKey(id);
    return mapper.map(materialDAO, MaterialDTO.class);
  }

  private PageDTO<MaterialListDTO> getMaterialList(MaterialListQry qry) {
    PageHelper.startPage((qry.getPage()-1)*qry.getSize(),qry.getSize());
    List<MaterialDAO> list = materialMapperEx.list(qry);
    return new PageDTO<>(list.stream().map(materialDAO ->mapper.map(materialDAO, MaterialListDTO.class))
        .collect(Collectors.toList()), ((Page)list).getTotal());
  }
}
