package com.miaojiaosan.jwt.dao;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {

  private Long id;

  private String name;

  private String code;

  @Override
  public String getAuthority() {
    return code;
  }

  @Override
  public String toString() {
    return code;
  }
}
