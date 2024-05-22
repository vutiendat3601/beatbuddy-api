// package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.adapter;

// import java.util.Optional;

// import org.springframework.stereotype.Repository;

// import lombok.RequiredArgsConstructor;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.port.outgoing.UserRepository;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.mapper.UserMapper;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.model.UserPo;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.infrastructure.repository.UserJpaRepository;

// @RequiredArgsConstructor
// @Repository
// public class UserJpaRepositoryAdapter implements UserRepository {

//   private final UserJpaRepository userJpaRepo;

//   @Override
//   public Optional<User> findById(String id) {
//     return userJpaRepo.findById(id).map(UserMapper::mapToUser);
//   }

//   @Override
//   public Optional<User> findByAuthUserId(String authUserId) {
//     return userJpaRepo.findByAuthUserId(authUserId).map(UserMapper::mapToUser);
//   }

//   @Override
//   public User save(User user) {
//     UserPo userPo = new UserPo(user.id(), user.authUserId());
//     userPo = userJpaRepo.save(userPo);
//     return UserMapper.mapToUser(userPo);
//   }
// }
