package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
