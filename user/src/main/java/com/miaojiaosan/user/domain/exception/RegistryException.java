package com.miaojiaosan.user.domain.exception;

import com.miaojiaosan.exception.BusinessException;

public class RegistryException extends BusinessException {
  public RegistryException() {
    super("注册失败");
  }
}
