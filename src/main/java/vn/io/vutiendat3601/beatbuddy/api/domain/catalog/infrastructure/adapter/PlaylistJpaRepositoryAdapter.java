package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.adapter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
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
            playlist.isPublic(),
            playlist.ownerId(),
            playlist.totalLikes(),
            playlist.isDeleted(),
            playlist.itemUrns());
    if (playlist.pkId() != null) {
      playlistJpaRepo
          .findById(playlist.pkId())
          .ifPresent(
              p -> {
                playlistPo.setPkId(p.getPkId());
                playlistPo.setCreatedAt(p.getCreatedAt());
                playlistPo.setCreatedBy(p.getCreatedBy());
              });
    }
    playlistJpaRepo.save(playlistPo);
  }

  @Transactional
  @Override
  public Optional<Playlist> findById(String id) {
    return playlistJpaRepo.findById(id).map(PlaylistMapper::mapToPlaylist);
  }

  @Override
  public Optional<Playlist> findByIdAndIsPublicTrue(String id) {
    return playlistJpaRepo.findByIdAndIsPublicTrue(id).map(PlaylistMapper::mapToPlaylist);
  }

  @Override
  public Pagination<Playlist> findAllByOwnerId(String ownerId, int page, int size) {
    final Pageable pageable = PageRequest.of(page, size);
    final Page<PlaylistPo> playlistPoPage = playlistJpaRepo.findAllByOwnerId(ownerId, pageable);
    final Pagination<Playlist> playlistPage =
        Pagination.of(playlistPoPage).map(PlaylistMapper::mapToPlaylist);
    return playlistPage;
  }
}
