package com.miaojiaosan.user.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户
 * @author miaojiaosan
 * @date 2020/4/25
 */
@FeignClient(name = "user", path = "/user/opt")
public interface UserOptApi {

}
