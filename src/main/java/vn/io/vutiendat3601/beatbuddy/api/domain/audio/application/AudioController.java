package vn.io.vutiendat3601.beatbuddy.api.domain.audio.application;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.application.model.AudioFileDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.port.incomming.Audio;

@SecurityRequirement(name = "web")
@Tag(name = "Audio")
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("v1/audio")
@RestController
public class AudioController {
  private final Audio audio;
  private final AudioPresenter audioPresenter;

  @Operation(summary = "Get Audio File", description = "Get an Audio File by id")
  @GetMapping(path = "files/{id}")
  public ResponseEntity<AudioFileDto> getAudioFilesById(@PathVariable String id) {
    return audioPresenter.presentAudioFile(audio.getAudioFileById(id));
  }

  @Operation(summary = "Get Audio Files by key", description = "Get Audio File by key")
  @GetMapping(path = "files")
  public ResponseEntity<List<AudioFileDto>> getAudioFilesByKey(
      @RequestParam(required = false, defaultValue = "") String key) {
    return audioPresenter.presentAudioFiles(audio.getAudioFilesByKey(key));
  }
}
