package com.loc.profile.mapper;

import org.mapstruct.Mapper;

import com.loc.profile.dto.request.ProfileCreationRequest;
import com.loc.profile.dto.response.ProfileResponse;
import com.loc.profile.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);

    ProfileResponse toProfileResponse(UserProfile entity);
}
