package com.miaojiaosan.user.dal.dao;

import com.miaojiaosan.common.dal.dao.BaseDAO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户账号数据访问对象
 * @author miaojiaosan
 * @date: 2020/4/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAccountDAO extends BaseDAO {
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
   *
   */
  private String nickname;
  /**
   *
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

}




