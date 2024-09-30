package org.callboard.services.postsService;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.PostResponseDTO;
import org.callboard.dto.postDto.UpdatePostRequestDTO;
import org.callboard.entities.Post;
import org.callboard.mappers.PostMappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService implements PostCRUDService<UpdatePostRequestDTO> {

    private final PostsRepoService repoService;
    PostMappers postMappers;

    @Override
    public ResponseEntity<PostResponseDTO> execute(UpdatePostRequestDTO dto) {
        Post postForUpdate = postMappers.toPost(dto);
        postForUpdate.setPostId(dto.getPostId());

        Post updatedPost = repoService.savePost(postForUpdate);
        return ResponseEntity.ok(postMappers.toPostResponseDTO(updatedPost));
    }
}
