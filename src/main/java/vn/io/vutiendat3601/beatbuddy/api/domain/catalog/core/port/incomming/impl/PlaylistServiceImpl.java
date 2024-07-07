package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.impl;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_ID_LENGTH;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_NOT_FOUND;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.PlaylistConstant.PLAYLIST_URN_PREFIX;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_TYPE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.AuthClient;
import vn.io.vutiendat3601.beatbuddy.api.client.auth.model.ResourceResponse;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.exception.PlaylistNotFoundException;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Playlist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.incomming.PlaylistService;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.PlaylistRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.TrackRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.PlaylistItem;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.ResourceUser;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.type.ScopePermission;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.util.UrnUtils;
import vn.io.vutiendat3601.beatbuddy.api.util.StringUtils;
import vn.io.vutiendat3601.beatbuddy.api.util.UserContext;

@RequiredArgsConstructor
@Service
public class PlaylistServiceImpl implements PlaylistService {
  private final TrackRepository trackRepo;
  private final PlaylistRepository playlistRepo;
  private final AuthClient authClient;

  @Override
  public Pagination<Playlist> getPopularPlaylists(int page, int size) {
    return playlistRepo.findByOrderByTotalLikesDesc(page, size);
  }

  @Override
  public void createPlaylist(String name, Boolean isPublic, String thumbnail, String description) {
    final String id = StringUtils.makeRandomString(PLAYLIST_ID_LENGTH);
    final String urn = PLAYLIST_URN_PREFIX + ":" + id;
    authClient.createResource(urn, name);
    final String ownerId = UserContext.getUserId();
    final Playlist playlist =
        Playlist.builder()
            .id(id)
            .urn(urn)
            .name(name)
            .thumbnail(thumbnail)
            .description(description)
            .isPublic(isPublic)
            .ownerId(ownerId)
            .build();
    playlistRepo.save(playlist);
  }

  @Override
  public Playlist getPlaylistById(String id) {
    final Playlist playlist =
        playlistRepo
            .findById(id)
            .orElseThrow(() -> new PlaylistNotFoundException(PLAYLIST_NOT_FOUND));
    getPlaylistItem(playlist.getItemUrns(), playlist.getItems());
    final ResponseEntity<ResourceResponse> httpResp = authClient.getResource(playlist.getUrn());
    if (httpResp.getStatusCode().is2xxSuccessful()) {
      final ResourceResponse resourceResp = httpResp.getBody();
      final ResourceUser owner =
          ResourceUser.builder()
              .id(resourceResp.getOwner().getId())
              .urn(resourceResp.getOwner().getUrn())
              .firstName(resourceResp.getOwner().getFirstName())
              .lastName(resourceResp.getOwner().getLastName())
              .picture(resourceResp.getOwner().getPicture())
              .build();

      final Set<ScopePermission> scopePermissions =
          resourceResp.getScopePermissions().stream()
              .map(
                  s -> {
                    final ResourceUser user =
                        ResourceUser.builder()
                            .id(s.getUser().getId())
                            .urn(s.getUser().getUrn())
                            .firstName(s.getUser().getFirstName())
                            .lastName(s.getUser().getLastName())
                            .picture(s.getUser().getPicture())
                            .build();
                    return ScopePermission.builder()
                        .isGranted(s.getIsGranted())
                        .scope(s.getScope())
                        .user(user)
                        .build();
                  })
              .collect(Collectors.toSet());
      playlist.setOwner(owner);
      playlist.setScopePermissions(scopePermissions);
    }

    return playlist;
  }

  @Override
  public void addItemToPlaylist(String id, List<String> itemUrns) {
    final Playlist playlist =
        playlistRepo
            .findById(id)
            .orElseThrow(() -> new PlaylistNotFoundException(PLAYLIST_NOT_FOUND));
    playlist.addItemUrnsAtHead(itemUrns);
    playlistRepo.save(playlist);
  }

  @Override
  public Pagination<Playlist> getUserPlaylists(int page, int size) {
    final String ownerId = UserContext.getUserId();
    return playlistRepo.findAllByOwnerId(ownerId, page, size);
  }

  private void getPlaylistItem(List<String> itemUrns, List<PlaylistItem> items) {
    for (String itemUrn : itemUrns) {
      final String type = UrnUtils.identifyType(itemUrn);
      if (type.equals(TRACK_TYPE)) {
        trackRepo
            .findByUrn(itemUrn)
            .ifPresent(
                (track) -> {
                  final PlaylistItem playlistItem =
                      PlaylistItem.builder()
                          .id(track.getId())
                          .urn(track.getUrn())
                          .name(track.getName())
                          .durationSec(track.getDurationSec())
                          .description(track.getDescription())
                          .releasedDate(track.getReleasedDate())
                          .thumbnail(track.getThumbnail())
                          .isPublic(track.getIsPublic())
                          .isPlayable(track.getIsPlayable())
                          .build();
                  for (Artist artist : track.getArtists()) {
                    playlistItem
                        .getArtists()
                        .add(
                            new PlaylistItem.Artist(
                                artist.getId(),
                                artist.getUrn(),
                                artist.getName(),
                                artist.getIsPublic(),
                                artist.getIsVerified(),
                                artist.getDescription(),
                                artist.getThumbnail()));
                  }
                  items.add(playlistItem);
                });
      }
    }
  }
}
