package com.miaojiaosan.utils;


import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class AccountUtil {

  public static Long id(HttpServletRequest req){
    String idStr = (Objects.isNull(idStr = req.getHeader("id"))?"-1":idStr);
    return Long.valueOf(idStr);
  }


  public static String account(HttpServletRequest req){
    return req.getHeader("account");
  }
}
