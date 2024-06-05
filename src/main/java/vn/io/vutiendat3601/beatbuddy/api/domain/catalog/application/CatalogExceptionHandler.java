package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import vn.io.vutiendat3601.beatbuddy.api.common.dto.ErrorDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.ArtistNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.PlaylistNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.TrackNotFoundException;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CatalogExceptionHandler {
  @ExceptionHandler({
    TrackNotFoundException.class,
    ArtistNotFoundException.class,
    PlaylistNotFoundException.class
  })
  public ResponseEntity<ErrorDto> handleCatalogNotFoundException(RuntimeException e) {
    log.error("{}: {}", e.getClass().getSimpleName(), e.getMessage());
    ErrorDto errorDto = new ErrorDto(NOT_FOUND, e.getMessage());
    return ResponseEntity.status(NOT_FOUND).body(errorDto);
  }

  // @ExceptionHandler(TrackNotFoundException.class)
  // public ResponseEntity<ErrorDto> handleTrackNotFoundException(TrackNotFoundException e) {
  //   log.error("TrackNotFoundException: {}", e.getMessage());
  //   ErrorDto errorDto = new ErrorDto(NOT_FOUND, e.getMessage());
  //   return ResponseEntity.status(NOT_FOUND).body(errorDto);
  // }

  // @ExceptionHandler(ArtistNotFoundException.class)
  // public ResponseEntity<ErrorDto> handleArtistNotFoundException(ArtistNotFoundException e) {
  //   log.error("ArtistNotFoundException: {}", e.getMessage());
  //   ErrorDto errorDto = new ErrorDto(NOT_FOUND, e.getMessage());
  //   return ResponseEntity.status(NOT_FOUND).body(errorDto);
  // }
}
