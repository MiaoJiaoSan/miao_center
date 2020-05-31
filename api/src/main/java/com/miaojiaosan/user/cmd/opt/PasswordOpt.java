package com.miaojiaosan.user.cmd.opt;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
  @Length(min = 8,max=16)
  private String oldPassword;

  /**
   * 新密码
   */
  @Length(min = 8,max=16)
  private String password;

  /**
   * 密码确认
   */
  @Length(min = 8,max=16)
  private String rePassword;
}
