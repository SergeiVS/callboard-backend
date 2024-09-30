package org.callboard.mappers;

import org.callboard.dto.postDto.NewPostRequestDTO;
import org.callboard.dto.postDto.PostResponseDTO;
import org.callboard.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMappers.class})

public interface PostMappers {

    Post toPost(NewPostRequestDTO dto);

    PostResponseDTO toPostResponseDTO(Post post);
}
