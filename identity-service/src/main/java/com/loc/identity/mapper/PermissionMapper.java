package com.loc.identity.mapper;

import org.mapstruct.Mapper;

import com.loc.identity.dto.request.PermissionRequest;
import com.loc.identity.dto.response.PermissionResponse;
import com.loc.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
