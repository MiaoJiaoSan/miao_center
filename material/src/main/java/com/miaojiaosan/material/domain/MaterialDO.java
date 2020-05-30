package com.miaojiaosan.material.domain;

import com.miaojiaosan.material.domain.data.Author;
import com.miaojiaosan.material.domain.data.Category;
import com.miaojiaosan.material.domain.data.Comment;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 素材
 * @author miaojiaosan
 * @date 2020/5/4
 */
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

  /**
   * 发布素材
   */
  public void release(){}

}
