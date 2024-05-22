package vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.port.outgoing;

import java.util.List;
import java.util.Optional;

import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model.AudioFile;

public interface AudioFileRepository {
  Optional<AudioFile> findById(String id);

  List<AudioFile> findAllByKey(String key);
}
