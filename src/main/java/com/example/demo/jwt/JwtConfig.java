package com.example.demo.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "application.jwt")
@Configuration
public class JwtConfig {

  private String secretKey;
  private Integer tokenExpirationAfterDays;

  public JwtConfig() {
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public Integer getTokenExpirationAfterDays() {
    return tokenExpirationAfterDays;
  }

  public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
    this.tokenExpirationAfterDays = tokenExpirationAfterDays;
  }

}