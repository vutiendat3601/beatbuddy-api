package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.PlaylistPo;

public interface PlaylistJpaRepository extends JpaRepository<PlaylistPo, UUID> {
  Optional<PlaylistPo> findById(String id);
}
