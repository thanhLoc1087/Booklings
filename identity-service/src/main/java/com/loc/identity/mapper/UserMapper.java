package com.loc.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.loc.identity.dto.request.UserCreationRequest;
import com.loc.identity.dto.request.UserUpdateRequest;
import com.loc.identity.dto.response.UserResponse;
import com.loc.identity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    User toUser(UserUpdateRequest request);

    // @Mapping(source="firstName", target="lastName")
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
