package com.miaojiaosan.matereial.domain;

import com.miaojiaosan.common.domain.data.Base;
import com.miaojiaosan.matereial.domain.data.Author;
import com.miaojiaosan.matereial.domain.data.Category;
import com.miaojiaosan.matereial.domain.data.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialDO extends Base {

  /**
   * 主键
   */
  private Long id;
  /**
   * 素材类别
   */
  private List<Category> categories;

  /**
   * 素材标题
   */
  private String title;

  /**
   * 阅读的人数
   */
  private Integer numberOfPeopleReading;

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
   * 发布时间
   */
  private Date releaseDate;

}
