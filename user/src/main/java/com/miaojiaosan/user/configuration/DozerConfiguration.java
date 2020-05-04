package com.miaojiaosan.user.configuration;

import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

/**
 * @author miaojiaosan
 * @date 2020/4/28
 */
@SpringBootConfiguration
public class DozerConfiguration {

  @Bean
  public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(@Value("classpath*:dozer/**/*.xml") Resource[] resources) throws Exception {
    final DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
    dozerBeanMapperFactoryBean.setMappingFiles(resources);
    return dozerBeanMapperFactoryBean;
  }


}
