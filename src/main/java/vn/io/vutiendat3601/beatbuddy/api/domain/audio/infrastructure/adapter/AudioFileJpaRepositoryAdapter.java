package vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.model.AudioFile;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.core.port.outgoing.AudioFileRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.mapper.AudioFileMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.repository.AudioFileJpaRepository;

@RequiredArgsConstructor
@Repository
public class AudioFileJpaRepositoryAdapter implements AudioFileRepository {
  private final AudioFileJpaRepository audioFileJpaRepo;

  @Override
  public Optional<AudioFile> findById(String id) {

    return audioFileJpaRepo.findById(id).map(AudioFileMapper::mapToAudioFile);
  }

  @Override
  public List<AudioFile> findAllByKey(String key) {
    return audioFileJpaRepo.findAllByKey(key).stream()
        .map(AudioFileMapper::mapToAudioFile)
        .collect(Collectors.toList());
  }
}
