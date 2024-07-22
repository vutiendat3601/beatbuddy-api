package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.presenter;

import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.ArtistConstant.ARTIST_TYPE;
import static vn.io.vutiendat3601.beatbuddy.api.domain.catalog.constant.TrackConstant.TRACK_TYPE;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.io.vutiendat3601.beatbuddy.api.common.dto.ResponseDto;
import vn.io.vutiendat3601.beatbuddy.api.common.type.Pagination;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.ArtistMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.LikeMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper.TrackMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.LikeDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.SearchDto;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Artist;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Like;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Track;

@Component
@RequiredArgsConstructor
public class CatalogPresenter {
  private final TrackMapper trackMapper;
  private final ArtistMapper artistMapper;

  public ResponseEntity<ResponseDto> presentResponseDtoOk(String message) {
    return ResponseEntity.ok(new ResponseDto(message, HttpStatus.OK));
  }

  public ResponseEntity<SearchDto> presentSearchDto(
      Set<Pagination<?>> results, Set<String> types, Integer page, Integer size) {
    final Map<String, Pagination<?>> resultsMap = new HashMap<>();
    results.forEach(
        result -> {
          if (!result.getItems().isEmpty()) {
            if (result.getItems().get(0) instanceof Track) {
              resultsMap.put(TRACK_TYPE, result.map(t -> trackMapper.mapToTrackDto((Track) t)));
            } else if (result.getItems().get(0) instanceof Artist) {
              resultsMap.put(ARTIST_TYPE, result.map(a -> artistMapper.mapToArtistDto((Artist) a)));
            }
          }
        });
    types.forEach(
        type -> {
          if (!resultsMap.containsKey(type)) {
            resultsMap.put(type, Pagination.empty());
          }
        });
    return ResponseEntity.ok(new SearchDto(resultsMap, types, page, size));
  }

  public ResponseEntity<LikeDto> presentLikeDto(Like like) {
    return ResponseEntity.ok(LikeMapper.mapToLikeDto(like));
  }
}
