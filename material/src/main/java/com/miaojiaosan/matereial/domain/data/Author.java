package com.miaojiaosan.matereial.domain.data;

import com.miaojiaosan.common.domain.data.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 作者
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Author extends Base {
  /**
   * 账号id
   */
  private Long accountId;
  /**
   * 评论人
   */
  private String nickName;

}
