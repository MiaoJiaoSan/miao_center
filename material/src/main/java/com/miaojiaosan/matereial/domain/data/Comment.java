package com.miaojiaosan.matereial.domain.data;

import com.miaojiaosan.common.domain.data.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评论
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends Base {

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
