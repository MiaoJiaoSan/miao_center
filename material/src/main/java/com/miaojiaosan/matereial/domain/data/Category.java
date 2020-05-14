package com.miaojiaosan.matereial.domain.data;

import com.miaojiaosan.common.domain.data.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分类
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class Category {
  /**
   * 分类编码
   */
  private String code;

  /**
   * 分类名称
   */
  private String name;
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
