package vn.io.vutiendat3601.beatbuddy.api.client.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
  private String message;

  private HttpStatus status;
}
