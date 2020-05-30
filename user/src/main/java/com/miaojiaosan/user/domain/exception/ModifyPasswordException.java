package com.miaojiaosan.user.domain.exception;

import com.miaojiaosan.exception.BusinessException;

/**
 * @author miaojiaosan
 */
public class ModifyPasswordException extends BusinessException {

  public ModifyPasswordException(){
    super("修改密码失败");
  }

}
