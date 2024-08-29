package com.loc.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.loc.identity.dto.request.RoleRequest;
import com.loc.identity.dto.response.RoleResponse;
import com.loc.identity.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
