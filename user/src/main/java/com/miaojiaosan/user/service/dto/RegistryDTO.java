package com.miaojiaosan.user.service.dto;

import com.miaojiaosan.common.service.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RegistryDTO extends BaseDTO {

  private String account;

  private String password;

  private String nickname;

  private String email;

  private String phone;
}
