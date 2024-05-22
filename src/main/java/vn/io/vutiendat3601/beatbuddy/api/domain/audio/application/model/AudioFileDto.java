package vn.io.vutiendat3601.beatbuddy.api.domain.audio.application.model;

import java.net.URI;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "AudioFile", description = "Schema for Audio File")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AudioFileDto {
  @Schema(description = "Key of the audio file in md5 format")
  private String key;

  @Schema(description = "List of URIs of the audio file")
  private Set<URI> uris;
}
