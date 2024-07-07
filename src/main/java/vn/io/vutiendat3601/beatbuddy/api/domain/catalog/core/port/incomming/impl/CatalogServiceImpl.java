package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.impl;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.ArtistConstant.ARTIST_TYPE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_TYPE;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.AuthClient;
import vn.io.vutiendat3601.beatbuddy.api.common.repository.SearchableRepository;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.common.type.SearchRequest;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Like;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.CatalogService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.ArtistRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.LikeRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.PlaylistRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.TrackRepository;
import vn.io.vutiendat3601.beatbuddy.api.util.StringUtils;
import vn.io.vutiendat3601.beatbuddy.api.util.UserContext;

@Service
public class CatalogServiceImpl implements CatalogService {
  private final LikeRepository likeRepo;
  private final Map<String, SearchableRepository<?>> searchableRepos;

  public CatalogServiceImpl(
      TrackRepository trackRepo,
      ArtistRepository artistRepo,
      PlaylistRepository playlistRepo,
      AuthClient authClient,
      LikeRepository likeRepo) {
    this.likeRepo = likeRepo;
    this.searchableRepos =
        Map.of(
            TRACK_TYPE, trackRepo,
            ARTIST_TYPE, artistRepo);
  }

  @Override
  public Set<Pagination<?>> search(String query, Set<String> types, int page, int size) {
    query = StringUtils.removeAccent(query);
    final SearchRequest searchReq = new SearchRequest(query, page, size);
    final Set<Pagination<?>> results = new LinkedHashSet<>();
    types.stream()
        .forEach(
            type ->
                Optional.ofNullable(searchableRepos.get(type))
                    .ifPresent(repo -> results.add(repo.findBySearchRequest(searchReq))));
    return results;
  }

  @Override
  public Like getCurrentUserLikeDetail() {
    final String ownerId = UserContext.getUserId();
    return likeRepo
        .findByOwnerId(ownerId)
        .orElseGet(
            () -> {
              final Like like = new Like(ownerId);
              likeRepo.save(like);
              return like;
            });
  }
}
