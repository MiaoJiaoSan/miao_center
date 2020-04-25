package com.miaojiaosan.user.cmd.opt;

import lombok.Data;

/**
 * 注册入参
 * @author miaojiaosan
 * @date: 2020/4/25
 */
@Data
public class RegistryOpt {

  private String account;

  private String password;

  private String nickname;

  private String email;

  private String phone;
}
