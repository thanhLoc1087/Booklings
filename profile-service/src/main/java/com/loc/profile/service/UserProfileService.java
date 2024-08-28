package com.loc.profile.service;

import org.springframework.stereotype.Service;

import com.loc.profile.dto.request.ProfileCreationRequest;
import com.loc.profile.dto.response.ProfileResponse;
import com.loc.profile.entity.UserProfile;
import com.loc.profile.mapper.UserProfileMapper;
import com.loc.profile.repository.UserProfileRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper profileMapper;

    public ProfileResponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = profileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);
        return profileMapper.toProfileResponse(userProfile);
    }

    public ProfileResponse getProfile(String profileId) {
        UserProfile userProfile =
                userProfileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Profile not Found"));

        return profileMapper.toProfileResponse(userProfile);
    }
}
