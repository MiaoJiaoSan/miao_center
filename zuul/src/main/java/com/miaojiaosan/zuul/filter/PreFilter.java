package com.miaojiaosan.zuul.filter;

import com.miaojiaosan.zuul.constant.Constant;
import com.miaojiaosan.zuul.request.RequestWrapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 调用下游系统前调用
 * @author miaojiaosan
 * @date 2020/05/16
 */
@Component
@Slf4j
public class PreFilter extends ZuulFilter {
  @Override
  public String filterType() {
    return FilterConstants.PRE_TYPE;
  }

  /**
   * 排序
   * @return 优先级
   */
  @Override
  public int filterOrder() {
    return 0;
  }

  /**
   *
   * @return 返回为true启用此过滤器
   */
  @Override
  public boolean shouldFilter() {
    //请求上下文
    RequestContext cxt = RequestContext.getCurrentContext();
    return cxt.sendZuulResponse();
  }

  @SneakyThrows
  @Override
  public Object run() {
    //请求上下文
    RequestContext cxt = RequestContext.getCurrentContext();
    //获取request
    HttpServletRequest request = cxt.getRequest();
    Map<String, String> accountInfo = Constant.getAccessToken(request.getHeader(Constant.AUTHORIZATION));
    if(!accountInfo.isEmpty()) {
      RequestWrapper requestWrapper = new RequestWrapper(request);
      requestWrapper.addHeader("id", String.valueOf(accountInfo.get("id")));
      String account = accountInfo.get("user_name");
      requestWrapper.addHeader("account", account);
      cxt.setRequest(requestWrapper);
      log.info("account:{} request to uri:{}", account, request.getRequestURI());
    }
    return null;
  }
}
