package com.loc.profile.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.loc.profile.dto.request.ProfileRequest;
import com.loc.profile.dto.response.ProfileResponse;
import com.loc.profile.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileRequest request);

    ProfileResponse toProfileResponse(UserProfile entity);

    void updateUserProfile(@MappingTarget UserProfile userProfile, ProfileRequest request);
}
