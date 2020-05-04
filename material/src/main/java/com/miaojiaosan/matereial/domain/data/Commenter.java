package com.miaojiaosan.matereial.domain.data;

import com.miaojiaosan.common.domain.data.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Commenter extends Base {
  /**
   * 账号id
   */
  private Long accountId;
  /**
   * 评论人
   */
  private String nickName;
}
