package com.miaojiaosan.user.domain.data;

import lombok.Data;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
public class Account {
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
