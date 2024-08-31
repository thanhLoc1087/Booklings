package com.loc.api_gateway.service;

import org.springframework.stereotype.Service;

import com.loc.api_gateway.dto.request.IntrospectRequest;
import com.loc.api_gateway.dto.response.ApiResponse;
import com.loc.api_gateway.dto.response.IntrospectResponse;
import com.loc.api_gateway.repository.IdentityClient;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;
    public Mono<ApiResponse<IntrospectResponse>> introspect(String token){
        return identityClient.introspect(
            IntrospectRequest.builder().token(token).build());
    }

}
