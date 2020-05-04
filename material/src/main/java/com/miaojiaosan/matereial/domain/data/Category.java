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
@EqualsAndHashCode(callSuper = true)
public class Category extends Base {
  /**
   * 分类编码
   */
  private String code;

  /**
   * 分类名称
   */
  private String name;
}
