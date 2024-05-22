package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
