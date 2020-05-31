package com.miaojiaosan.user.cmd.opt;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 修改用户信息
 * @author miaojiaosan
 */
@Data
public class PersonChangeOpt {
  /**
   * account
   */
  private Long accountId;
  /**
   *
   */
  @Length(max=64)
  private String nickname;
  /**
   *
   */
  @Length(max=255)
  private String picture;
  /**
   * 姓名
   */
  @Length(min = 2,max=64)
  private String name;
  /**
   * 年龄
   */
  @Min(7)
  private Integer age;
  /**
   * 性别
   */
  @Min(1)
  @Max(4)
  private Integer gender;
  /**
   * 出生日期
   */
  @NotNull
  private Date birthday;
  /**
   * 证件类型
   */
  @Min(1)
  @Max(7)
  private Integer certificatesType;
  /**
   * 证件号
   */
  @Length(max=64)
  private String certificates;
  /**
   * 邮箱
   */
  @Length(max=64)
  private String email;
  /**
   * 电话
   */
  @Length(max=64)
  private String phone;


}
