package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.adapter;

import java.util.LinkedList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.Like;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.LikeRepository;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper.LikeMapper;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.LikePo;
import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.repository.LikeJpaRepository;

@Repository
@RequiredArgsConstructor
public class LikeJpaRespositoryAdapter implements LikeRepository {
  private final LikeJpaRepository likeJpaRepo;

  @Override
  public Optional<Like> findByOwnerId(String ownerId) {
    return likeJpaRepo.findByOwnerId(ownerId).map(LikeMapper::mapToLike);
  }

  @Override
  public void save(Like like) {
    if (like.getPkId() == null) {
      final LikePo likePo = new LikePo(new LinkedList<>(like.getUrns()), like.getOwnerId());
      likeJpaRepo.save(likePo);
    } else {
      likeJpaRepo
          .findById(like.getPkId())
          .ifPresent(
              likePo -> {
                likePo.setUrns(new LinkedList<>(like.getUrns()));
                likeJpaRepo.save(likePo);
              });
    }
  }
}
