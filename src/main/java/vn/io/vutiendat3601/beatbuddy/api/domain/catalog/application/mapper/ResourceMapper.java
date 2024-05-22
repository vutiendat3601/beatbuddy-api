// package vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.mapper;

// import java.util.Set;

// import vn.io.vutiendat3601.beatbuddy.api.domain.auth.core.model.Resource;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ResourceDto;
// import vn.io.vutiendat3601.beatbuddy.api.domain.catalog.application.model.ResourcePermissionDto;

// public interface ResourceMapper {

//   static ResourceDto mapToResourceDto(Resource resource) {
//     final Set<ResourcePermission> permissions = resource.permissions();
//     final Set<ResourcePermissionDto> grantedPermissions =
//         ResourcePermissionMapper.mapToPermissionsByUser(permissions, true);
//     final Set<ResourcePermissionDto> pendingPermissions =
//         ResourcePermissionMapper.mapToPermissionsByUser(permissions, false);
//     return new ResourceDto(
//         resource.urn(),
//         resource.name(),
//         resource.isPublic(),
//         resource.scopes(),
//         grantedPermissions,
//         pendingPermissions);
//   }
// }
