package com.miaojiaosan.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author miaojiaosan
 * @date 2020/5/6
 * keytool -genkeypair -alias configuration -keyalg RSA -keystore configuration.jks -keypass miaojiaosan  -storepass miaojiaosan
 * keytool -list -rfc --keystore configuration.jks | openssl x509 -inform pem -pubkey
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigurationApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigurationApplication.class, args);
  }
}
