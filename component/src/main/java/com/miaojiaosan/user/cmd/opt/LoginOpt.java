package com.miaojiaosan.user.cmd.opt;

import lombok.Data;

/**
 * 登录入参
 * @author miaojiaosan
 * @date: 2020/4/25
 */
@Data
public class LoginOpt {

  private String account;

  private String password;

  private String code;
}
