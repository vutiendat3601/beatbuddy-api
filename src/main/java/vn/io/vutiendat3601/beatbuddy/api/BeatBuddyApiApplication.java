package vn.io.vutiendat3601.beatbuddy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BeatBuddyApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(BeatBuddyApiApplication.class, args);
  }
}
