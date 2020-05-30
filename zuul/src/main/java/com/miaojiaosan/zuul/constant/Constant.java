package com.miaojiaosan.zuul.constant;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author miaojiaosan
 */
public class Constant {

  public static final String AUTHORIZATION = "Authorization";
  public static final String BEARER = "^(B|b)earer.+";


  public static Map<String, String> getAccessToken(String accessToken) throws IOException {
    HashMap<String, String> empty = new HashMap<>(0);
    if (StringUtils.isEmpty(accessToken)) {
      return empty;
    } else if (accessToken.matches(BEARER)) {
      return new ObjectMapper()
          .readValue(JwtHelper.decode(accessToken.split(" ")[1]).getClaims(), Map.class);
    } else {
      return empty;
    }
  }
}
