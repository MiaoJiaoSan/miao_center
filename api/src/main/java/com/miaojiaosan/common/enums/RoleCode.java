package com.miaojiaosan.common.enums;

/**
 * 角色枚举
 * @author miaojiaosan
 * @date 2015/05/16
 */
public enum RoleCode {
    /**
     * 普通用户
     */
  NORMAL("普通用户");

    /**
     * 角色名称
     */
  private String name;

    RoleCode(String name) {
    this.name = name;
  }

  public static String getCode(String name){
      for (RoleCode code: values()){
          return code.name();
      }
      return NORMAL.name();
  }

  public String getName() {
    return name;
  }
}
