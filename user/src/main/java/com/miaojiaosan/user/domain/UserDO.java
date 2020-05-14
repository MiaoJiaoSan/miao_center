package com.miaojiaosan.user.domain;

import com.miaojiaosan.user.domain.data.Account;
import lombok.Data;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
public class UserDO {

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
  private Long modifyTime;

  public void registry(){
//    account.setValidate(1);
  }

  public boolean login(Account account){
    return this.account.getPassword().equals(account.getPassword());
  }
}

