package vn.io.vutiendat3601.beatbuddy.api.domain.auth.config;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource.ResourceType;
import vn.io.vutiendat3601.beatbuddy.api.domain.auth.type.resource.ResourceUrn;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@ConfigurationProperties(prefix = "auth.resource")
public class AuthResourceConfig {
  private ResourceUrn urn;

  private Set<ResourceType> types;
}
