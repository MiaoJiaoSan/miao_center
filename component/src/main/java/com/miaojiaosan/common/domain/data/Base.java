package com.miaojiaosan.common.domain.data;

import lombok.Data;

/**
 * @author miaojiaosan
 * @date: 2020/4/25
 */
@Data
public class Base {
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
