package com.miaojiaosan.user.api;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.user.vo.AccountVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author miaojiaosan
 * @date 2020/4/25
 */
@FeignClient(name = "zuul", path = "/user-proxy/account/qry")
public interface AccountQryApi {
  /**
   * 根据id查询账号信息
   * @param id 账号id
   * @return 账号信息
   */
  @GetMapping(path={"/id/{id}"})
  Result<AccountVO> byId(@PathVariable("id") Long id);
}
