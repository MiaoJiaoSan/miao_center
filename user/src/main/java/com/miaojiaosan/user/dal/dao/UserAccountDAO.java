package com.miaojiaosan.user.dal.dao;

import lombok.Data;

import java.util.Date;

/**
 * 用户账号数据访问对象
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
public class UserAccountDAO {
  /**
   * 主键
   */
  private Long id;
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
   * 图像
   */
  private String picture;
  /**
   * 邮箱
   */
  private String email;
  /**
   * 电话
   */
  private String phone;
  /**
   * 验证
   */
  private Integer validate;
  /**
   * 用户id
   */
  private Long userId;
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




