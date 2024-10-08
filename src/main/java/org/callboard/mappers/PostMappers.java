package org.callboard.mappers;

import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.dto.postDto.UpdatePostRequest;
import org.callboard.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMappers.class, SubjectMappers.class})
public interface PostMappers {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "postId",ignore = true)
    @Mapping(target = "subject", ignore = true)
    Post toPost(NewPostRequest request);

    PostResponse toPostResponse(Post post);
}
