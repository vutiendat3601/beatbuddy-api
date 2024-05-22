package vn.io.vutiendat3601.beatbuddy.api.domain.auth.application.presenter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import vn.io.vutiendat3601.beatbuddy.api.common.dto.ResponseDto;

@Component
public class ResourcePresenter {
  public ResponseEntity<ResponseDto> presentResponseOk(String message) {
    return ResponseEntity.ok(new ResponseDto(message, HttpStatus.OK));
  }
}
