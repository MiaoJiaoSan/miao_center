package com.miaojiaosan.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 下游系统完成后调用
 * @author miaojiaosan
 * @date 2020/05/16
 */
@Component
public class PostFilter extends ZuulFilter {
  @Override
  public String filterType() {
    return FilterConstants.POST_TYPE;
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return false;
  }

  @Override
  public Object run() throws ZuulException {
    return null;
  }
}
