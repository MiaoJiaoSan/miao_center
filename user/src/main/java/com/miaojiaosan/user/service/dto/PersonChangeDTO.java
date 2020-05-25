package com.miaojiaosan.user.service.dto;

import lombok.Data;

import java.util.Date;

/**
 * 修改用户信息DTO
 * @author miaojiaosan
 */
@Data
public class PersonChangeDTO {

  /**
   * 账号
   */
  private Long id;
  /**
   *
   */
  private String nickname;
  /**
   *
   */
  private String picture;
  /**
   * 姓名
   */
  private String name;
  /**
   * 年龄
   */
  private Integer age;
  /**
   * 性别
   */
  private Integer gender;
  /**
   * 出生日期
   */
  private Date birthday;
  /**
   * 证件类型
   */
  private Integer certificatesType;
  /**
   * 证件号
   */
  private String certificates;
  /**
   * 邮箱
   */
  private String email;
  /**
   * 电话
   */
  private String phone;
}
