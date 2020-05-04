package com.miaojiaosan.user.service.dto;

import com.miaojiaosan.common.service.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountDTO extends BaseDTO {
  /**
   * 主键
   */
  private Long id;
  /**
   * 账号
   */
  private String account;
  /**
   * 名称
   */
  private String nickname;
  /**
   * 图像
   */
  private String picture;
  /**
   * 邮箱
   */
  private String email;
  /**
   * 电话
   */
  private String phone;
  /**
   * 验证
   */
  private Integer validate;
  /**
   * 用户id
   */
  private Long userId;
}

