package com.miaojiaosan.material.domain;

import com.miaojiaosan.exception.ModifyException;
import com.miaojiaosan.generate.IdGenerate;
import com.miaojiaosan.material.cmd.opt.ReleaseOpt;
import com.miaojiaosan.material.domain.data.Author;
import com.miaojiaosan.material.domain.data.Comment;
import com.miaojiaosan.material.exception.RecycleException;
import com.miaojiaosan.material.exception.ReleaseException;
import com.miaojiaosan.material.repository.MaterialRepository;
import com.miaojiaosan.user.api.AccountQryApi;
import com.miaojiaosan.user.vo.AccountVO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.dozer.Mapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 素材
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
public class MaterialDO {

  /**
   * 主键
   */
  private Long id;
  /**
   * 素材类别
   */
  private Integer categories;

  /**
   * 素材标题
   */
  private String title;

  /**
   * 内容
   */
  private String content;

  /**
   * 阅读的人数
   */
  private Integer numberOfPeopleReading;

  /**
   * 赞
   */
  private Integer fabulous;
  /**
   * 评论
   */
  private List<Comment> comments;

  /**
   * 作者
   */
  private Author author;

  /**
   * 原创  1：原创  2：转载
   */
  private Integer isOriginal;

  /**
   * 草稿 0草稿 1发布
   */
  private Integer state;
  /**
   * 发布时间
   */
  private Date releaseDate;
  /**
   * 版本
   */
  private Long version;
  /**
   *  操作人
   */
  private Long modify;
  /**
   * 操作时间
   */
  private Long modifyTime;

  @Resource
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private AccountQryApi accountQryApi;
  @Resource
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private MaterialRepository materialRepository;
  @Resource
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Mapper mapper;
  @Resource
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private IdGenerate idGenerate;

  /**
   * 发布/暂存
   * @param opt {@link ReleaseOpt}
   */
  public void release(ReleaseOpt opt){
    if(Objects.equals(this.state,1)){
      //表示已经发布不额嗯再次发布
      throw new ReleaseException();
    }else if(Objects.isNull(this.id)){
      //表示新增
      //组装数据
      mapper.map(opt, this);
      this.id = idGenerate.nextId();
      AccountVO vo = accountQryApi.byId(opt.getAccountId()).getValue();
      Author author = new Author();
      author.setId(vo.getId());
      author.setNickname(vo.getNickname());
      this.author = author;
      //持久化
      materialRepository.add(this);
    }else{
      //修改
      if(!Objects.equals(this.author.getId(), opt.getAccountId())){
        throw new ModifyException();
      }
      //组装数据
      this.title = opt.getTitle();
      this.content = opt.getContent();
      this.categories = opt.getCategories();
      this.state = opt.getState();
      this.isOriginal = opt.getIsOriginal();
      this.modifyTime = null;
      this.releaseDate = null;
      this.version += 1;
      materialRepository.modify(this);
    }
  }

  /**
   * 删除
   * @param accountId 素材id
   */
  public void recycle(Long accountId) {
    if(!Objects.equals(this.author.getId(),accountId)){
      throw new RecycleException();
    }
    materialRepository.remove(this);
  }

}
