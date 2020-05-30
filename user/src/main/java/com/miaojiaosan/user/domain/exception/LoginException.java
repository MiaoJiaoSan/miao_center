package com.miaojiaosan.user.domain.exception;

import com.miaojiaosan.exception.BusinessException;

/**
 * @author miaojiaosan
 */
public class LoginException extends BusinessException {

  public LoginException(){
    super("登陆失败");
  }
}
