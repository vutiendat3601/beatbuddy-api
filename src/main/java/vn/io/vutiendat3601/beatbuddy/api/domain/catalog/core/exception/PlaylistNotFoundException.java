package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception;

public class PlaylistNotFoundException extends RuntimeException {
  public PlaylistNotFoundException(String message) {
    super(message);
  }
}
