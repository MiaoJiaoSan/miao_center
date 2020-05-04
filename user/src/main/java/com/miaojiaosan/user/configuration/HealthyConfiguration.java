package com.miaojiaosan.user.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@SpringBootConfiguration
public class HealthyConfiguration implements HealthIndicator {
  @Override
  public Health health() {
    return Health.up().build();
  }
}
