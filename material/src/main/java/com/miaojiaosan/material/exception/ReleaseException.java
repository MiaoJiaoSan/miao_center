package com.miaojiaosan.material.exception;

import com.miaojiaosan.exception.BusinessException;

public class ReleaseException extends BusinessException {
  public ReleaseException() {
    super("发布错误");
  }
}
