package com.miaojiaosan.user.domain;

import com.miaojiaosan.common.dto.Token;
import com.miaojiaosan.exception.ModifyException;
import com.miaojiaosan.generate.IdGenerate;
import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.PasswordOpt;
import com.miaojiaosan.user.cmd.opt.PersonChangeOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;
import com.miaojiaosan.user.dal.dao.UserRoleDAO;
import com.miaojiaosan.user.dal.mapperex.UserRoleMapperEx;
import com.miaojiaosan.user.domain.data.Account;
import com.miaojiaosan.user.domain.data.Role;
import com.miaojiaosan.user.domain.exception.LoginException;
import com.miaojiaosan.user.domain.exception.ModifyPasswordException;
import com.miaojiaosan.user.domain.exception.RegistryException;
import com.miaojiaosan.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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


  @Resource
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private UserRepository userRepository;
  @Resource
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private RestTemplate restTemplate;
  @Resource
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private Mapper mapper;
  @Resource
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private IdGenerate idGenerate;
  @Value("${security.oauth2.client.access-token-uri}")
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private String accessTokenUri;
  @Resource
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private UserRoleMapperEx userRoleMapperEx;

  /**
   * 注册
   * @param opt {@link RegistryOpt}
   */
  public void registry(RegistryOpt opt){
    //初始化user对象
    registryLoad(opt);
    //密码加密
    String password = this.account.getPassword();
    this.account.setPassword(BCRYPT + new BCryptPasswordEncoder().encode(password));
    //持久化userDO
    if(!userRepository.add(this)){
      throw new RegistryException();
    }
    //还原密码 登陆校验
    this.account.setPassword(password);
  }

  /**
   * 登录
   * @param opt {@link LoginOpt}
   */
  public void login(LoginOpt opt){
    //加载账号信息
    loginLoad(opt);
    //密码校验
    if(Objects.isNull(this.account)
        || !new BCryptPasswordEncoder().matches(opt.getPassword()
        ,this.account.getPassword().substring(BCRYPT.length()))){
      throw new LoginException();
    }
    //设置未加密密码,准备登陆
    this.account.setPassword(opt.getPassword());
    Token token = jwtValidate();
    userRepository.refreshToken(this);
    account.setAccessToken(token.getToken_type() + " " + token.getAccess_token());
  }



  //--------------------------------------------------------------------------------------------------------------------




  /**
   * 修改用户信息
   * @param opt {@link PersonChangeOpt}
   */
  public void change(PersonChangeOpt opt){
    userRepository.accountById(opt.getAccountId(), this);
    if(Objects.isNull(this.account)){
      throw new ModifyException();
    }
    Long id = this.id;
    mapper.map(opt,this);
    this.id = id;
    userRepository.modifyUser(this);
  }

  /**
   * 修改密码
   * @param opt {@link PasswordOpt}
   */
  public void password(PasswordOpt opt) {
    //加载账号信息

    userRepository.accountById(opt.getId(),this);
    if(Objects.isNull(this.account)
        ||!Objects.equals(opt.getId(),this.account.getId())
        ||!new BCryptPasswordEncoder().matches(opt.getOldPassword()
            , this.account.getPassword().substring(BCRYPT.length()))
        || !opt.getPassword().equals(opt.getRePassword())){
      throw new ModifyPasswordException();
    }
    this.account.setPassword(BCRYPT+ new BCryptPasswordEncoder().encode(opt.getPassword()));
    userRepository.modifyAccount(this);
  }

  private void registryLoad(RegistryOpt opt) {
    CompletableFuture.supplyAsync(() -> {
      mapper.map(opt, this);
      this.generateId();
      return this;
    }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
      Account account = mapper.map(opt, Account.class);
      account.setId(idGenerate.nextId());
      account.setRefreshToken("");
      return account;
    }), (userDO, account) -> {
      userDO.setAccount(account);
      return userDO;
    }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
      UserRoleDAO normal = userRoleMapperEx.byCode("NORMAL");
      List<UserRoleDAO> userRoleLst = Collections.singletonList(normal);
      return userRoleLst.stream()
          .map(role ->  mapper.map(role, Role.class)).collect(Collectors.toList());
    }),(userDO, roles) -> {
      userDO.setRoles(roles);
      return userDO;
    }).join();
  }


  private void loginLoad(LoginOpt opt) {
    userRepository.accountByAccount(opt.getAccount(),this);
  }

  private Token jwtValidate() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth("web", "miaojiaosan");
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "password");
    params.add("scope", "all");
    params.add("username", this.account.getAccount());
    params.add("password", this.account.getPassword());
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
    ResponseEntity<Token> exchange = restTemplate.exchange(accessTokenUri, HttpMethod.POST, entity, Token.class);
    Token token = exchange.getBody();
    assert token != null;
    account.setRefreshToken(token.getRefresh_token());
    return token;
  }


  private void generateId(){
    this.id = idGenerate.nextId();

  }

}

