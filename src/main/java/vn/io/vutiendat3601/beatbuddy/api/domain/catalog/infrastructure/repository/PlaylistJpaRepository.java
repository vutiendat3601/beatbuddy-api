package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.PlaylistPo;

public interface PlaylistJpaRepository extends JpaRepository<PlaylistPo, UUID> {
  Optional<PlaylistPo> findById(String id);

  Page<PlaylistPo> findAllByOwnerId(String ownerId, Pageable pageable);

  Optional<PlaylistPo> findByIdAndIsPublicTrue(String id);
}
