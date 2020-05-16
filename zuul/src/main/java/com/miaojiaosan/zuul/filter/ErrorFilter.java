package com.miaojiaosan.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * erroe
 * @author miaojiaosan
 * @date 2020/05/16
 */
@Component
public class ErrorFilter extends ZuulFilter {
  @Override
  public String filterType() {
    return FilterConstants.ERROR_TYPE;
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
