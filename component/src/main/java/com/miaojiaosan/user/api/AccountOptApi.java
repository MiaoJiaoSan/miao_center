package com.miaojiaosan.user.api;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.cmd.opt.LoginOpt;
import com.miaojiaosan.user.cmd.opt.RegistryOpt;
import com.miaojiaosan.user.vo.AccountVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 用户
 * @author miaojiaosan
 * @date 2020/4/25
 */
@FeignClient(name = "user", path = "/account/opt")
public interface AccountOptApi {
  /**
   * 注册
   * @param registryOpt {@link RegistryOpt}
   * @return true 注册成功
   */
  @PostMapping("/registry")
  Result<AccountVO> registry(RegistryOpt registryOpt);
  /**
   * 登录
   * @param loginOpt {@link LoginOpt}
   * @return true 登录成功
   */
  @PostMapping("/login")
  Result<Boolean> login(LoginOpt loginOpt);
}
