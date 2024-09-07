package com.loc.post.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loc.post.dto.ApiResponse;
import com.loc.post.dto.request.PostRequest;
import com.loc.post.dto.response.PostResponse;
import com.loc.post.service.PostService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping("/create")
    public ApiResponse<PostResponse> createPost(@RequestBody PostRequest request) {
        return ApiResponse.<PostResponse>builder()
            .result(postService.createPost(request))
            .build();
    }
    
    @GetMapping("/my-posts")
    public ApiResponse<List<PostResponse>> getMyPosts() {
        return ApiResponse.<List<PostResponse>>builder()
            .result(postService.getMyPosts())
            .build();
    }
}