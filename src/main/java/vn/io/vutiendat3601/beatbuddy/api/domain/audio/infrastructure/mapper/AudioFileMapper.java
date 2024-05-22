package vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.mapper;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model.AudioFile;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.model.AudioFilePo;

public interface AudioFileMapper {
  static AudioFile mapToAudioFile(AudioFilePo afPo) {
    final Set<URI> uris = new HashSet<>();
    uris.add(URI.create(afPo.getUri()));
    return new AudioFile(afPo.getKey(), uris);
  }
}
