package org.callboard.mappers;

import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMappers.class, SubjectMappers.class})
public interface PostMappers {


    PostResponse toPostResponse(Post post);
}
