package com.miaojiaosan.matereial.domain.data;

import lombok.Data;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class Commenter {
  /**
   * 账号id
   */
  private Long accountId;
  /**
   * 评论人
   */
  private String nickName;
}
