package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.client;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.io.vutiendat3601.beatbuddy.api.client.AuthClient;
import vn.io.vutiendat3601.beatbuddy.api.client.model.Response;
import vn.io.vutiendat3601.beatbuddy.api.client.model.UserDetailResponse;
import vn.io.vutiendat3601.beatbuddy.api.client.model.UserResponse;

@FeignClient(name = "auth", url = "${client.auth.url}")
public interface AuthFeignClient extends AuthClient {
  @Override
  @GetMapping("v1/auth/users/me")
  ResponseEntity<UserDetailResponse> getCurrentUserDetail();

  @Override
  @GetMapping("v1/auth/users/{id}")
  ResponseEntity<UserResponse> getUser(@PathVariable String id);

  @Override
  @GetMapping("v1/auth/users")
  ResponseEntity<List<UserResponse>> getUsers(Set<String> urns);

  @Override
  @PostMapping("v1/auth/resources")
  ResponseEntity<Response> createResource(@RequestParam String urn, @RequestParam String name);
}
