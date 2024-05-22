package vn.io.vutiendat3601.beatbuddy.api.common.dto;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ErrorDto {
  private final HttpStatus status;

  private final String errorMessage;

  private final ZonedDateTime errorTime = ZonedDateTime.now();
}
