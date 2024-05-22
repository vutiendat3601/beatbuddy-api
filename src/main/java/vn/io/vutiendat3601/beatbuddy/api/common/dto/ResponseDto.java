package vn.io.vutiendat3601.beatbuddy.api.common.dto;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold response information")
@AllArgsConstructor
@Data
public class ResponseDto {
  private String message;

  private HttpStatus status;
}
