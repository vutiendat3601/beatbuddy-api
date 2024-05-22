package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception;

public class TrackNotFoundException extends RuntimeException {
  public TrackNotFoundException(String message) {
    super(message);
  }
}
