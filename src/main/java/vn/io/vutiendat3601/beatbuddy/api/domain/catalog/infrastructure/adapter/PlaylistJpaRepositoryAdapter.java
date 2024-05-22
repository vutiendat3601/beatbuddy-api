package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.PlaylistRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper.PlaylistMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.PlaylistPo;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.repository.PlaylistJpaRepository;

@RequiredArgsConstructor
@Repository
public class PlaylistJpaRepositoryAdapter implements PlaylistRepository {
  private final PlaylistJpaRepository playlistJpaRepo;

  @Override
  public void save(Playlist playlist) {
    final PlaylistPo playlistPo =
        new PlaylistPo(
            playlist.id(),
            playlist.urn(),
            playlist.name(),
            playlist.thumbnail(),
            playlist.description(),
            playlist.ownerId(),
            playlist.isPublic());
    playlistJpaRepo.save(playlistPo);
  }

  @Transactional
  @Override
  public Optional<Playlist> findById(String id) {
    return playlistJpaRepo.findById(id).map(PlaylistMapper::mapToPlaylist);
  }
}
