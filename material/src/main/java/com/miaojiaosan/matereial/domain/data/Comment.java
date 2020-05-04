package com.miaojiaosan.matereial.domain.data;

import lombok.Data;

import java.util.Date;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class Comment {

  /**
   * 素材id
   */
  private Long materialId;

  /**
   * 评论主键
   */
  private Long commentId;

  /**
   * 发布人
   */
  private Commenter  commenter;

  /**
   * 发布时间
   */
  private Date releaseDate;
}
