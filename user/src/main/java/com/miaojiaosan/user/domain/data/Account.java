package com.miaojiaosan.user.domain.data;

import com.miaojiaosan.common.domain.data.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Account extends Base {
  /**
   * 主键
   */
  private Long id;
  /**
   * 账号
   */
  private String account;
  /**
   * 密码
   */
  private String password;
  /**
   *
   */
  private String nickname;
  /**
   *
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
   * 实名
   */
  private Integer validate;
  /**
   * token
   */
  private String token;
}
