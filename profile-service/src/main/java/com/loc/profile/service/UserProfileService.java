package com.loc.profile.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.loc.profile.dto.request.ProfileRequest;
import com.loc.profile.dto.response.ProfileResponse;
import com.loc.profile.entity.UserProfile;
import com.loc.profile.exception.AppException;
import com.loc.profile.exception.ErrorCode;
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

    public ProfileResponse createProfile(ProfileRequest request) {
        UserProfile userProfile = profileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);
        return profileMapper.toProfileResponse(userProfile);
    }

    public ProfileResponse getProfile(String profileId) {
        UserProfile userProfile =
                userProfileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Profile not Found"));

        return profileMapper.toProfileResponse(userProfile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<ProfileResponse> getProfiles() {
        return userProfileRepository.findAll().stream()
                .map(profileMapper::toProfileResponse)
                .toList();
    }

    public ProfileResponse updateProfile(String profileId, ProfileRequest request) {
        UserProfile userProfile = userProfileRepository
                .findById(profileId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
        profileMapper.updateUserProfile(userProfile, request);
        userProfileRepository.save(userProfile);
        return profileMapper.toProfileResponse(userProfile);
    }

    public ProfileResponse getMyProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        var profile = userProfileRepository
                .findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        return profileMapper.toProfileResponse(profile);
    }
}
