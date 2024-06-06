package vn.io.vutiendat3601.beatbuddy.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import vn.io.vutiendat3601.beatbuddy.api.util.ProfilesResolver;

@ActiveProfiles(resolver = ProfilesResolver.class)
@SpringBootTest
class ApiApplicationTests {
  @Test
  void contextLoads() {}
}
