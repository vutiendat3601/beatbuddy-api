package vn.io.vutiendat3601.beatbuddy.api.application.config.client;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.io.vutiendat3601.beatbuddy.api.util.UserContext;

@Configuration
public class FeignClientConfig {
  @Bean
  RequestInterceptor injectJwtInterceptor() {
    return reqTemplate -> {
      final String jwtAuthenticationToken = UserContext.getJwtAuthenticationToken();
      reqTemplate.header(AUTHORIZATION, "Bearer " + jwtAuthenticationToken);
    };
  }
}
