package com.miaojiaosan.zuul.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaojiaosan.user.dal.dao.UserAccountDAO;
import com.miaojiaosan.user.dal.mapperex.UserAccountMapperEx;
import com.miaojiaosan.zuul.constant.Constant;
import com.miaojiaosan.zuul.request.RequestWrapper;
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

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author miaojiaosan
 */
@SpringBootConfiguration
public class RefreshTokenAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

  public static final String ERROR = "error";


  private WebResponseExceptionTranslator<?> exceptionTranslator = new DefaultWebResponseExceptionTranslator();

  @Resource
  private UserAccountMapperEx userAccountMapperEx;

  @Resource
  RestTemplate restTemplate;

  @Value("${security.oauth2.client.access-token-uri}")
  private String accessTokenUri;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
    try {
      String accessToken = request.getHeader(Constant.AUTHORIZATION);
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
    UserAccountDAO account = getUserAccount(accessToken);
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
   * 解析出账号信息
   * @param accessToken accessToken
   * @return {@link UserAccountDAO}
   * @throws IOException 异常
   */
  private UserAccountDAO getUserAccount(String accessToken) throws IOException {
    Map<String, String> map = Constant.getAccessToken(accessToken);
    return userAccountMapperEx.byAccount(map.get("user_name"));
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
    RequestWrapper requestWrapper = new RequestWrapper(request);
    requestWrapper.addHeader(Constant.AUTHORIZATION, accessToken);
    //如果刷新成功则存储cookie并且跳转到原来需要访问的页面
    response.addHeader(Constant.AUTHORIZATION, accessToken);
    request.getRequestDispatcher(request.getRequestURI()).forward(requestWrapper, response);
  }
}
