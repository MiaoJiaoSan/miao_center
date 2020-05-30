package com.miaojiaosan.user.domain;

import com.miaojiaosan.user.domain.exception.LoginException;
import com.miaojiaosan.user.domain.exception.ModifyException;
import com.miaojiaosan.user.domain.exception.ModifyPasswordException;
import com.miaojiaosan.user.domain.exception.RegistryException;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.data.Role;
import com.miaojiaosan.user.service.dto.LoginDTO;
import com.miaojiaosan.user.service.dto.PasswordDTO;
import com.miaojiaosan.user.service.dto.PersonChangeDTO;
import com.miaojiaosan.user.service.dto.RegistryDTO;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Objects;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Data
public class UserDO {

  public static final String BCRYPT = "{bcrypt}";
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
   * 角色
   */
  private List<Role> roles;
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

  public void registry(RegistryDTO registryDTO){
    if(Objects.isNull(registryDTO.getAccount())){
      throw new RegistryException();
    }
    this.account.setPassword(BCRYPT + new BCryptPasswordEncoder().encode(registryDTO.getPassword()));
  }

  public void registryAfter(RegistryDTO registryDTO){
    this.account.setPassword(registryDTO.getPassword());
  }

  public void login(LoginDTO loginDTO){
    if(!new BCryptPasswordEncoder()
        .matches(loginDTO.getPassword()
            ,this.account.getPassword().substring(BCRYPT.length()))){
      throw new LoginException();
    }
    this.account.setPassword(loginDTO.getPassword());
  }

  public void change(PersonChangeDTO personChangeDTO){
    if(!Objects.equals(personChangeDTO.getId(),this.account.getId())){
      throw new ModifyException();
    }
    //修改账号信息
    this.name = personChangeDTO.getName();
    this.age = personChangeDTO.getAge();
    this.certificatesType = personChangeDTO.getCertificatesType();
    this.certificates = personChangeDTO.getCertificates();
    this.modify = this.id;
    String email = personChangeDTO.getEmail();
    String phone = personChangeDTO.getPhone();
    this.email = email;
    this.phone = phone;

    //修改用户信息
    this.account.setEmail(email);
    this.account.setPassword(phone);
    this.account.setModify(this.id);
    this.account.setNickname(personChangeDTO.getNickname());
    this.account.setPicture(personChangeDTO.getPicture());
  }

  public void password(PasswordDTO passwordDTO) {
    if(!Objects.equals(passwordDTO.getId(),this.account.getId()) &&
        this.account.getPassword().equals(passwordDTO.getOldPassword())
        && passwordDTO.getPassword().equals(passwordDTO.getRePassword())){
      throw new ModifyPasswordException();
    }
    this.account.setPassword(BCRYPT+ new BCryptPasswordEncoder().encode(passwordDTO.getPassword()));
  }
}

