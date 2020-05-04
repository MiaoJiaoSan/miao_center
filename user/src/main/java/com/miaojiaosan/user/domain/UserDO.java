package com.miaojiaosan.user.domain;

import com.miaojiaosan.common.domain.data.Base;
import com.miaojiaosan.user.domain.data.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDO extends Base {

  /**
   * 主键
   */
  private Long id;
  /**
   * 姓名
   */
  private String name;
  /**
   * 年龄
   */
  private Integer age;
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
  /**
   * 账号
   */
  private Account account;

  public void registry(){
//    account.setValidate(1);
  }

  public boolean login(Account account){
    return this.account.getPassword().equals(account.getPassword());
  }
}

