package com.miaojiaosan.material.domain.data;

import lombok.Data;

/**
 * 作者
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class Author {
  /**
   * 账号id
   */
  private Long id;
  /**
   * 账号
   */
  private String account;
  /**
   * 评论人
   */
  private String nickname;
  /**
   *
   */
  private String picture;

}
