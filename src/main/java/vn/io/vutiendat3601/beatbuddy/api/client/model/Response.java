package vn.io.vutiendat3601.beatbuddy.api.client.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
  private String message;

  private HttpStatus status;
}
