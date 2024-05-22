package vn.io.vutiendat3601.beatbuddy.api.domain.audio.application;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.application.model.AudioFileDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model.AudioFile;

@Component
public class AudioPresenter {
  public ResponseEntity<AudioFileDto> presentAudioFile(AudioFile audioFile) {
    final AudioFileDto audioFileDto = new AudioFileDto(audioFile.key(), audioFile.uris());
    return ResponseEntity.ok(audioFileDto);
  }

  public ResponseEntity<List<AudioFileDto>> presentAudioFiles(List<AudioFile> audioFiles) {
    final List<AudioFileDto> audioFileDtos =
        audioFiles.stream()
            .map(audioFile -> new AudioFileDto(audioFile.key(), audioFile.uris()))
            .toList();
    return ResponseEntity.ok(audioFileDtos);
  }
}
