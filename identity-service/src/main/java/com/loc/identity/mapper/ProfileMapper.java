package com.loc.identity.mapper;

import org.mapstruct.Mapper;

import com.loc.identity.dto.request.ProfileCreationRequest;
import com.loc.identity.dto.request.UserCreationRequest;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
