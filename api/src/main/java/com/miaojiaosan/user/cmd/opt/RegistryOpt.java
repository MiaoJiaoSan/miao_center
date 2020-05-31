package com.miaojiaosan.user.cmd.opt;

import lombok.Data;

/**
 * 注册入参
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
public class RegistryOpt {
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
}
