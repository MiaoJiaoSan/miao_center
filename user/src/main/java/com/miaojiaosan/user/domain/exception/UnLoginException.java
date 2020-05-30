package com.miaojiaosan.user.domain.exception;

import com.miaojiaosan.exception.BusinessException;

public class UnLoginException extends BusinessException {
  public UnLoginException() {
    super("请先登陆");
  }
}
