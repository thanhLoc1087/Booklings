package com.loc.identity.repository.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.loc.identity.dto.request.ProfileCreationRequest;
import com.loc.identity.dto.response.ProfileResponse;

@FeignClient(name = "profile-service", url = "${application.service.profile}")
public interface ProfileClient {
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfileResponse createProfile(@RequestBody ProfileCreationRequest request);
}
