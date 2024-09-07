package com.loc.post.mapper;

import org.mapstruct.Mapper;

import com.loc.post.dto.response.PostResponse;
import com.loc.post.entity.Post;

@Mapper(componentModel="spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
