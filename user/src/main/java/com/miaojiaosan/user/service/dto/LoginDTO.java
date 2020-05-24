package com.miaojiaosan.user.service.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class LoginDTO {
  /**
   * 账号
   */
  private String account;
  /**
   * 密码
   */
  private String password;
  /**
   * 名称
   */
  private String nickname;
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
