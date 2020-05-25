package com.miaojiaosan.user.service.dto;

import lombok.Data;

/**
 *
 * @author miaojiaosan
 */
@Data
public class PasswordDTO {

  private Long id;
  /**
   * 旧密码
   */
  private String oldPassword;

  /**
   * 新密码
   */
  private String password;

  /**
   * 密码确认
   */
  private String rePassword;
}
