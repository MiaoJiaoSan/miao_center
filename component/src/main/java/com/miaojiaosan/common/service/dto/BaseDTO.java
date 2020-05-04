package com.miaojiaosan.common.service.dto;

import lombok.Data;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class BaseDTO {
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
