package org.callboard.services.postsService;

import org.callboard.dto.postDto.NewPostRequestDTO;
import org.callboard.dto.postDto.PostResponseDTO;
import org.springframework.http.ResponseEntity;

public interface PostCRUDService<T> {

    ResponseEntity<PostResponseDTO> execute(T t);
}
