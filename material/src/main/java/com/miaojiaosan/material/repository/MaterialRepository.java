package com.miaojiaosan.material.repository;

import com.miaojiaosan.generate.IdGenerate;
import com.miaojiaosan.material.dal.dao.MaterialDAO;
import com.miaojiaosan.material.dal.mapper.MaterialMapper;
import com.miaojiaosan.material.dal.mapperex.MaterialMapperEx;
import com.miaojiaosan.material.domain.MaterialDO;
import com.miaojiaosan.material.domain.data.Author;
import com.miaojiaosan.material.service.dto.ReleaseDTO;
import com.miaojiaosan.user.api.AccountQryApi;
import com.miaojiaosan.user.vo.AccountVO;
import org.dozer.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 */
@Repository
public class MaterialRepository {

  @Resource
  private IdGenerate idGenerate;

  @Resource
  private AccountQryApi accountQryApi;

  @Resource
  private Mapper mapper;

  @Resource
  private MaterialMapperEx materialMapperEx;


  public MaterialDO create(ReleaseDTO dto) {
    AccountVO account = accountQryApi.byId(dto.getAccountId()).getValue();
    MaterialDO materialDO = mapper.map(dto, MaterialDO.class);
    materialDO.setId(idGenerate.nextId());
    Author author = mapper.map(dto, Author.class);
    author.setId(account.getId());
    author.setNickname(account.getNickname());
    author.setPicture(account.getPicture());
    materialDO.setAuthor(author);
    return materialDO;
  }

  @Transactional(rollbackFor = Exception.class)
  public void add(MaterialDO materialDO) {
    Author author = materialDO.getAuthor();
    MaterialDAO materialDAO = mapper.map(materialDO, MaterialDAO.class);
    Long id = author.getId();
    materialDAO.setAccountId(id);
    materialDAO.setModify(id);
    materialMapperEx.insert(materialDAO);
  }
}
