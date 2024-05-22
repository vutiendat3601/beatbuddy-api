package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception;

public class ArtistNotFoundException extends RuntimeException {
  public ArtistNotFoundException(String message) {
    super(message);
  }
}
