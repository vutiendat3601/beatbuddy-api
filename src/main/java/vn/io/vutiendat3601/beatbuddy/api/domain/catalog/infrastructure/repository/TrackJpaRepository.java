package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.TrackPo;

public interface TrackJpaRepository extends JpaRepository<TrackPo, UUID> {
  Optional<TrackPo> findById(String id);

  List<TrackPo> findByIdIn(Iterable<String> ids);

  Page<TrackPo> findByArtistsIdOrderByTotalLikesDesc(String artistId, Pageable pageable);

  Page<TrackPo> findByOrderByTotalLikesDesc(Pageable pageable);

  @Query(
      value =
          """
          SELECT * FROM tracks WHERE tags ILIKE %:query% OR tsv @@ to_tsquery(:tsvQuery)
          ORDER BY total_likes DESC
          """,
      nativeQuery = true)
  Page<TrackPo> findByTagOrTsvOrderByTotalLikesDesc(
      @Param("query") String query, @Param("tsvQuery") String tsvQuery, Pageable pageable);

  Optional<TrackPo> findByUrn(String urn);
}
