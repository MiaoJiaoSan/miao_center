package com.miaojiaosan.user.service.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class RegistryDTO {
  /**
   * 账号
   */
  private String account;
  /**
   * 密码
   */
  private String password;
  /**
   * 名称
   */
  private String nickname;
  /**
   * 邮箱
   */
  private String email;
  /**
   * 电话
   */
  private String phone;
  /**
   * 版本
   */
  private Long version;
  /**
   *  操作人
   */
  private Long modify;
  /**
   * 操作时间
   */
  private Date modifyTime;
}
