package com.miaojiaosan.user.domain.exception;

import com.miaojiaosan.exception.BusinessException;

public class ModifyException extends BusinessException {
  public ModifyException() {
    super("修改失败");
  }
}
