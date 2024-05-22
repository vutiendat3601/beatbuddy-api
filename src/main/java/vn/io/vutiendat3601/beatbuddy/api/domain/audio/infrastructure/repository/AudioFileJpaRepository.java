package vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.io.vutiendat3601.beatbuddy.api.domain.audio.infrastructure.model.AudioFilePo;

public interface AudioFileJpaRepository extends JpaRepository<AudioFilePo, UUID> {
  Optional<AudioFilePo> findById(String id);

  List<AudioFilePo> findAllByKey(String key);
}
