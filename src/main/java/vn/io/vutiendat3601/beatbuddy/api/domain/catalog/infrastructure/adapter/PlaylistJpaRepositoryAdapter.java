package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.adapter;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.common.type.SearchRequest;
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
            playlist.getId(),
            playlist.getUrn(),
            playlist.getName(),
            playlist.getThumbnail(),
            playlist.getDescription(),
            playlist.getIsPublic(),
            playlist.getOwnerId(),
            playlist.getTotalLikes(),
            playlist.getIsDeleted(),
            playlist.getItemUrns());
    if (playlist.getPkId() != null) {
      playlistJpaRepo
          .findById(playlist.getPkId())
          .ifPresent(
              p -> {
                playlistPo.setPkId(p.getPkId());
                playlistPo.setCreatedAt(p.getCreatedAt());
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

  @Override
  public Pagination<Playlist> findByOrderByTotalLikesDesc(int page, int size) {
    final Page<PlaylistPo> playlistPoPage =
        playlistJpaRepo.findByOrderByTotalLikesDesc(PageRequest.of(page, size));
    final Page<Playlist> playlistPage = playlistPoPage.map(PlaylistMapper::mapToPlaylist);
    return Pagination.of(playlistPage);
  }

  @Override
  public Pagination<Playlist> findBySearchRequest(SearchRequest searchReq) {
    throw new UnsupportedOperationException("Unimplemented method 'findBySearchRequest'");
  }
}
