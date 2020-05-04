package com.miaojiaosan.user.vo;

import com.miaojiaosan.common.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountVO extends BaseVO {

  /**
   * 主键
   */
  private Long id;
  /**
   * 账号
   */
  private String account;
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
}
