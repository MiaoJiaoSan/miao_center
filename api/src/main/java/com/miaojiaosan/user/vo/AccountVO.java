package com.miaojiaosan.user.vo;

import lombok.Data;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
public class AccountVO {

  /**
   *
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
   * token
   */
  private String accessToken;
}
