package com.miaojiaosan.user.cmd.opt;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 注册入参
 *
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
public class RegistryOpt {
  /**
   * 账号
   */
  @Length(min = 8, max = 16)
  private String account;
  /**
   * 密码
   */
  @Length(min = 8,max=16)
  private String password;
  /**
   * 名称
   */
  @Length(min = 2,max=16)
  private String nickname;
  /**
   * 邮箱
   */
  @Length(max=64)
  private String email;
  /**
   * 电话
   */
  @Length(max=64)
  private String phone;
}
