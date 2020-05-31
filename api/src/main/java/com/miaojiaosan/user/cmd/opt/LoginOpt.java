package com.miaojiaosan.user.cmd.opt;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 登录入参
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
public class LoginOpt {

  @Length(min = 8,max=16)
  private String account;

  @Length(min = 8,max=16)
  private String password;

  private String accessToken;
}
