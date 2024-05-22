package vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model.exception;

public class AudioFileNotFoundException extends RuntimeException {
  public AudioFileNotFoundException(String message) {
    super(message);
  }
}
