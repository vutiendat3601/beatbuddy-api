package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.impl;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.ArtistConstant.ARTIST_NOT_FOUND;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.ArtistNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.ArtistService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.CatalogService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.ArtistRepository;

@RequiredArgsConstructor
@Service
public class ArtistServiceImpl implements ArtistService {
  private final ArtistRepository artistRepo;
  private final CatalogService catalogService;

  @Override
  public Artist getArtistById(String id) {
    return artistRepo.findById(id).orElseThrow(() -> new ArtistNotFoundException(ARTIST_NOT_FOUND));
  }

  @Override
  public List<Artist> getArtistByIds(List<String> ids) {
    return artistRepo.findByIds(ids);
  }

  @Override
  public Pagination<Artist> getPopularArtists(int page, int size) {
    return artistRepo.findByOrderByTotalLikesDesc(page, size);
  }

  @Override
  public void likeArtist(String id) {
    artistRepo
        .findById(id)
        .ifPresentOrElse(
            artist -> catalogService.addLike(artist.getUrn()),
            () -> new ArtistNotFoundException(ARTIST_NOT_FOUND));
  }

  @Override
  public void unlikeArtist(String id) {
    artistRepo
        .findById(id)
        .ifPresentOrElse(
            artist -> catalogService.removeLike(artist.getUrn()),
            () -> new ArtistNotFoundException(ARTIST_NOT_FOUND));
  }
}
