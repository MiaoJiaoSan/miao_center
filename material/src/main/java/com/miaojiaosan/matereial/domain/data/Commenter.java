package com.miaojiaosan.matereial.domain.data;

import lombok.Data;

/**
 * 评论人
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class Commenter  {
  /**
   * 账号id
   */
  private Long accountId;
  /**
   * 评论人
   */
  private String nickName;
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
