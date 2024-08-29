package com.loc.profile.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loc.profile.dto.request.ProfileRequest;
import com.loc.profile.dto.response.ApiResponse;
import com.loc.profile.dto.response.ProfileResponse;
import com.loc.profile.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @PostMapping("/users")
    public ApiResponse<ProfileResponse> createProfile(@RequestBody ProfileRequest request) {
        return ApiResponse.<ProfileResponse>builder()
                .result(userProfileService.createProfile(request))
                .build();
    }

    @GetMapping("/{profileId}")
    public ApiResponse<ProfileResponse> getUserProfile(@PathVariable String profileId) {
        return ApiResponse.<ProfileResponse>builder()
                .result(userProfileService.getProfile(profileId))
                .build();
    }

    @GetMapping("/users")
    public ApiResponse<List<ProfileResponse>> getUserProfiles() {
        return ApiResponse.<List<ProfileResponse>>builder()
                .result(userProfileService.getProfiles())
                .build();
    }

    @PostMapping("/{profileId}")
    public ApiResponse<ProfileResponse> updateProfile(
            @PathVariable String profileId, @RequestBody ProfileRequest request) {
        return ApiResponse.<ProfileResponse>builder()
                .result(userProfileService.updateProfile(profileId, request))
                .build();
    }
}
