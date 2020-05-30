package com.miaojiaosan.user.api;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.cmd.opt.PersonChangeOpt;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户
 * @author miaojiaosan
 * @date 2020/4/25
 */
@FeignClient(name = "user", path = "/user/opt")
public interface UserOptApi {

  /**
   * 修改用户信息
   * @param personChangeOpt {@link PersonChangeOpt}
   * @return true 成功
   */
  Result<Boolean> change(PersonChangeOpt personChangeOpt);
}
