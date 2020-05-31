package com.miaojiaosan.material.repository;

import com.miaojiaosan.material.dal.dao.MaterialDAO;
import com.miaojiaosan.material.dal.mapperex.MaterialMapperEx;
import com.miaojiaosan.material.domain.MaterialDO;
import com.miaojiaosan.material.domain.data.Author;
import org.dozer.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author miaojiaosan
 */
@Repository
public class MaterialRepository {
  @Resource
  private Mapper mapper;
  @Resource
  private MaterialMapperEx materialMapperEx;

  @Transactional(rollbackFor = Exception.class)
  public void add(MaterialDO materialDO) {
    Author author = materialDO.getAuthor();
    MaterialDAO materialDAO = mapper.map(materialDO, MaterialDAO.class);
    Long id = author.getId();
    materialDAO.setAccountId(id);
    materialDAO.setNickname(author.getNickname());
    materialDAO.setModify(id);
    materialMapperEx.insert(materialDAO);
  }

  @Transactional(rollbackFor = Exception.class)
  public void remove(MaterialDO materialDO) {
    materialMapperEx.deleteByPrimaryKey(materialDO.getId());
  }

  @Transactional(rollbackFor = Exception.class)
  public void modify(MaterialDO materialDO) {
    Author author = materialDO.getAuthor();
    MaterialDAO materialDAO = mapper.map(materialDO, MaterialDAO.class);
    materialDAO.setModify(author.getId());
    materialMapperEx.modify(materialDAO);
  }


  public void byId(Long id, MaterialDO materialDO) {
    //id为空 表示新增
    if(Objects.isNull(id)){
      return;
    }
    MaterialDAO materialDAO = materialMapperEx.selectByPrimaryKey(id);
    mapper.map(materialDAO, materialDO);
    Author author = new Author();
    author.setId(materialDAO.getAccountId());
    materialDO.setAuthor(author);
  }
}
