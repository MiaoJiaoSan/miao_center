package com.miaojiaosan.user.utils;

import com.miaojiaosan.user.domain.exception.UnLoginException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class AccountUtil {

  public static Long id(HttpServletRequest req){
    String idStr = req.getHeader("id");
    if(Objects.isNull(idStr)){
      throw new UnLoginException();
    }
    return Long.valueOf(idStr);
  }


  public static String account(HttpServletRequest req){
    return req.getHeader("account");
  }
}
