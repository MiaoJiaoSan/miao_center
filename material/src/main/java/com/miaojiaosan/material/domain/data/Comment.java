package com.miaojiaosan.material.domain.data;

import lombok.Data;

import java.util.Date;

/**
 * 评论
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class Comment{
  /**
   *
   */
  private Long id;
  /**
   * 素材id
   */
  private Long materialId;
  /**
   *
   */
  private String content;
  /**
   * 发布人
   */
  private Commenter  commenter;

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
}
