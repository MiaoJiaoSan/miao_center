package com.miaojiaosan.jwt.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecretUtil {

  public static void main(String[] args) {
    String miaojiaosan ="{bcrypt}"+ new BCryptPasswordEncoder().encode("demo");
    System.out.println(miaojiaosan);
  }
}
