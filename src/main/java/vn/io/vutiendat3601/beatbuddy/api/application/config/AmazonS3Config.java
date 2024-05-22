package vn.io.vutiendat3601.beatbuddy.api.application.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Configuration
@ConfigurationProperties(prefix = "amazon.s3")
public class AmazonS3Config {
  private String accessKey;

  private String secretKey;

  private String serviceEndpoint;

  private String region;

  private String bucketName;

  private List<String> cdnBaseUrls;

  // @Bean
  AmazonS3 amazonS3() {
    AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    return AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withEndpointConfiguration(new EndpointConfiguration(serviceEndpoint, region))
        .build();
  }
}
