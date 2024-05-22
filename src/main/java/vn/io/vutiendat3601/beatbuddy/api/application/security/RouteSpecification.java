package vn.io.vutiendat3601.beatbuddy.api.application.security;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RouteSpecification {
  private List<String> uris = new LinkedList<>();

  private HttpMethod method;

  private List<String> authorities = new LinkedList<>();
}
