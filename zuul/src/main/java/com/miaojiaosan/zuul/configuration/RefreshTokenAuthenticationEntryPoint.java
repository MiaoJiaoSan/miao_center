package com.miaojiaosan.zuul.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import com.miaojiaosan.user.dal.mapperex.UserAccountMapperEx;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author miaojiaosan
 */
@SpringBootConfiguration
public class RefreshTokenAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

  public static final String ERROR = "error";
  public static final String AUTHORIZATION = "Authorization";

  private WebResponseExceptionTranslator<?> exceptionTranslator = new DefaultWebResponseExceptionTranslator();

  @Resource
  private UserAccountMapperEx userAccountMapperEx;

  @Resource
  private ObjectMapper objectMapper;

  @Resource
  RestTemplate restTemplate;

  @Value("${security.oauth2.client.access-token-uri}")
  private String accessTokenUri;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
    try {
      String accessToken = request.getHeader(AUTHORIZATION);
      assert accessToken != null;
      //解析异常，如果是401则处理
      ResponseEntity<?> result = exceptionTranslator.translate(authException);
      if (result.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        Map<String, String> responseInfo = refresh(accessToken);
        if (responseInfo.get(ERROR) != null) {
          errorResponse(response, responseInfo);
        } else {
          requestWrapper(request, response, responseInfo);
        }
      } else {
        //如果不是401异常，则以默认的方法继续处理其他异常
        super.commence(request, response, authException);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 刷新
   * @param accessToken accessToken
   * @return JWT Response
   * @throws IOException 异常
   */
  @SuppressWarnings("unchecked")
  private Map<String, String> refresh(String accessToken) throws IOException {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    BASE64Decoder decoder = new BASE64Decoder();
    String decode = new String(decoder.decodeBuffer(accessToken.split(" ")[1].split("\\.")[1]));
    Map<String, String> map = objectMapper.readValue(decode, Map.class);
    UserAccountDAO account = userAccountMapperEx.byAccount(map.get("user_name"));
    formData.add("grant_type", "refresh_token");
    formData.add("refresh_token", account.getRefreshToken());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth("web","miaojiaosan");
    Map<String, String> responseInfo = restTemplate.exchange(accessTokenUri, HttpMethod.POST,
        new HttpEntity<>(formData, headers), Map.class).getBody();
    //如果刷新异常
    assert responseInfo != null;
    return responseInfo;
  }

  /**
   * 刷新token失败
   * @param response 异常响应
   * @param responseInfo JWT Response
   * @throws IOException 异常
   */
  private void errorResponse(HttpServletResponse response, Map<String, String> responseInfo) throws IOException {
    // 返回指定格式的错误信息
    response.setStatus(401);
    response.setHeader("Content-Type", "application/json;charset=utf-8");
    response.getWriter().print("{\"code\":1,\"message\":\"" + responseInfo.get("error_description") + "\"}");
    response.getWriter().flush();
  }

  /**
   * 刷新请求头,重定向
   * @param request {@link HttpServletRequest }
   * @param response {@link HttpServletResponse }
   * @param responseInfo {@link Map }
   * @throws ServletException 异常
   * @throws IOException 异常
   */
  private void requestWrapper(HttpServletRequest request, HttpServletResponse response, Map<String, String> responseInfo) throws ServletException, IOException {
    String accessToken = responseInfo.get("token_type") + " " + responseInfo.get("access_token");
    HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request){
      private Map<String, String> headerMap = new HashMap<>(1);
      public HttpServletRequestWrapper addHeader(String name, String value) {
        headerMap.put(name, value);
        return this;
      }

      @Override
      public String getHeader(String name) {
        String headerValue = super.getHeader(name);
        if (headerMap.containsKey(name)) {
          headerValue = headerMap.get(name);
        }
        return headerValue;
      }

      @Override
      public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(headerMap.keySet());
        return Collections.enumeration(names);
      }

      @Override
      public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (headerMap.containsKey(name)) {
          values = Collections.singletonList(headerMap.get(name));
        }
        return Collections.enumeration(values);
      }
    }.addHeader(AUTHORIZATION, accessToken);
    //如果刷新成功则存储cookie并且跳转到原来需要访问的页面
    response.addHeader(AUTHORIZATION, accessToken);
    request.getRequestDispatcher(request.getRequestURI()).forward(requestWrapper, response);
  }
}
