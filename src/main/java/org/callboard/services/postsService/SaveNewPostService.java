package org.callboard.services.postsService;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.NewPostRequestDTO;
import org.callboard.dto.postDto.PostResponseDTO;
import org.callboard.entities.Post;
import org.callboard.entities.User;
import org.callboard.exceptions.NotFoundException;
import org.callboard.mappers.PostMappers;
import org.callboard.services.userServices.UserRepositoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveNewPostService implements PostCRUDService<NewPostRequestDTO> {
    private final PostsRepoService repoService;
    private final PostMappers postMappers;
    private final UserRepositoryService userRepositoryService;

    @Override
    public ResponseEntity<PostResponseDTO> execute(NewPostRequestDTO dto) {

        Post postForSave = getPostFromDto(dto);

        Post savedPost = repoService.savePost(postForSave);

        PostResponseDTO postResponseDTO = postMappers.toPostResponseDTO(savedPost);
        return new ResponseEntity<>(postResponseDTO, HttpStatus.CREATED);
    }

    private @NotNull Post getPostFromDto(NewPostRequestDTO dto) {
        User user = userRepositoryService.findUserById(dto.getUserId())
                .orElseThrow(() -> new NotFoundException(STR."User with id: \{dto.getUserId()} not found"));
        Post postForSave = postMappers.toPost(dto);
        postForSave.setUser(user);
        return postForSave;
    }
}
