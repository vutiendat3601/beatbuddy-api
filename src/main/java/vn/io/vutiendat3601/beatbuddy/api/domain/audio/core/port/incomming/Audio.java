package vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.port.incomming;

import java.util.List;

import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model.AudioFile;

public interface Audio {
  AudioFile getAudioFileById(String id);

  List<AudioFile> getAudioFilesByKey(String key);
}
