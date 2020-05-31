package com.miaojiaosan.user.cmd.opt;

import lombok.Data;

/**
 * 重置账号密码
 * @author miaojiaosan
 */
@Data
public class PasswordOpt {
  /**
   * 用户id
   */
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
