package com.loc.post.service;

import java.time.Instant;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.loc.post.dto.request.PostRequest;
import com.loc.post.dto.response.PageResponse;
import com.loc.post.dto.response.PostResponse;
import com.loc.post.entity.Post;
import com.loc.post.mapper.PostMapper;
import com.loc.post.repository.PostRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {

    PostRepository postRepository;
    PostMapper postMapper;

    public PostResponse createPost(PostRequest request) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        Post post = Post.builder()
            .userId(authentication.getName())
            .content(request.getContent())
            .createdDate(Instant.now())
            .modifiedDate(Instant.now())
            .build();

        post = postRepository.save(post);
        
        return postMapper.toPostResponse(post);
    }

    public PageResponse<PostResponse> getMyPosts(int page, int size) {
        var userId = SecurityContextHolder.getContext().getAuthentication().getName();

        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageData = postRepository.findAllByUserId(userId, pageable);
        return PageResponse.<PostResponse>builder()
            .currentPage(page)
            .pageSize(pageData.getSize())
            .totalPages(pageData.getTotalPages())
            .totalElements(pageData.getTotalElements())
            .data(pageData.getContent().stream().map(postMapper::toPostResponse).toList())
            .build();
    }
}
