package vn.io.vutiendat3601.beatbuddy.api.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import lombok.extern.slf4j.Slf4j;
import vn.io.vutiendat3601.beatbuddy.api.common.dto.ErrorDto;

@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException e) {
    log.error("RuntimeException: {}", e.getMessage());
    return ResponseEntity.internalServerError()
        .body(new ErrorDto(INTERNAL_SERVER_ERROR, e.getMessage()));
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<ErrorDto> handleValidationException(HandlerMethodValidationException e) {
    log.error("HandlerMethodValidationException: {}", e.getMessage());
    String errorMessage = "Validation error";
    if (!e.getAllErrors().isEmpty()) {
      errorMessage = e.getAllErrors().get(0).getDefaultMessage();
    }
    return ResponseEntity.badRequest().body(new ErrorDto(BAD_REQUEST, errorMessage));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e, BindingResult bindingResult) {
    log.error("MethodArgumentNotValidException: {}", e.getMessage());
    String errorMessage = "Validation error";
    if (!bindingResult.getAllErrors().isEmpty()) {
      errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
    }
    return ResponseEntity.badRequest().body(new ErrorDto(BAD_REQUEST, errorMessage));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {
    log.error("HttpMessageNotReadableException: {}", e.getMessage());
    final String errorMessage = "Wrong request body format, please check API documentation";
    return ResponseEntity.badRequest().body(new ErrorDto(BAD_REQUEST, errorMessage));
  }
}
