// package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

// import java.util.List;
// import java.util.Map;
// import java.util.Set;
// import java.util.function.Predicate;
// import java.util.stream.Collectors;
// import java.util.stream.StreamSupport;

// import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.ResourcePermission;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ResourcePermissionDto;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.core.model.User;

// public interface ResourcePermissionMapper {
//   Predicate<ResourcePermission> grantedPermissionFilter = ResourcePermission::isGranted;

//   static Set<ResourcePermissionDto> mapToPermissionsByUser(
//       Iterable<ResourcePermission> permissions, boolean isGranted) {
//     final Predicate<ResourcePermission> filter =
//         isGranted ? grantedPermissionFilter : grantedPermissionFilter.negate();
//     final Map<User, List<ResourcePermission>> permissionsGroupByUser =
//         StreamSupport.stream(permissions.spliterator(), false)
//             .filter(filter)
//             .collect(Collectors.groupingBy(ResourcePermission::user));
//     return permissionsGroupByUser.entrySet().stream()
//         .map(
//             entry -> {
//               final User user = entry.getKey();
//               final List<ResourcePermission> filteredPermissions = entry.getValue();
//               final Set<String> scopes =
//                   filteredPermissions.stream()
//                       .map(ResourcePermission::scope)
//                       .collect(Collectors.toSet());
//               final ResourcePermissionDto resourcePermissionDto =
//                   new ResourcePermissionDto(scopes, isGranted, UserMapper.mapToUserDto(user));
//               return resourcePermissionDto;
//             })
//         .collect(Collectors.toSet());
//   }
// }
