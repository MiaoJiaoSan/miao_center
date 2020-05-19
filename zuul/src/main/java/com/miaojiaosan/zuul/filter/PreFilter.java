package com.miaojiaosan.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 调用下游系统前调用
 * @author miaojiaosan
 * @date 2020/05/16
 */
@Component
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

  @Override
  public Object run() throws ZuulException {
    //请求上下文
    RequestContext cxt = RequestContext.getCurrentContext();
    //获取request
    HttpServletRequest request = cxt.getRequest();
    System.out.println("=========="+request.getRequestURL()+"=========");
    return null;
  }
}
