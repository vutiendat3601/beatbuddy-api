package vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.port.incomming.facade;

import static vn.io.vutiendat3601.beatbuddy.api.domain.audio.constant.AudioFileConstant.AUDIO_FILE_NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model.AudioFile;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model.exception.AudioFileNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.port.incomming.Audio;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.port.outgoing.AudioFileRepository;

@RequiredArgsConstructor
@Service
public class AudioFacade implements Audio {
  @Qualifier("audioFileJpaRepositoryAdapter")
  private final AudioFileRepository audioFileRepo;

  @Override
  public AudioFile getAudioFileById(String id) {
    return audioFileRepo
        .findById(id)
        .orElseThrow(() -> new AudioFileNotFoundException(AUDIO_FILE_NOT_FOUND));
  }

  @Override
  public List<AudioFile> getAudioFilesByKey(String key) {
    return audioFileRepo.findAllByKey(key);
  }
}
