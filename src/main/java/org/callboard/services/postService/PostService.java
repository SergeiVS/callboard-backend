package org.callboard.services.postService;

import org.callboard.dto.postDto.NewPostRequestDTO;
import org.callboard.dto.postDto.PostResponseDTO;
import org.callboard.dto.postDto.UpdatePostRequestDTO;

import java.util.List;

public interface PostService {
    PostResponseDTO createPost(NewPostRequestDTO request);
    List<PostResponseDTO> getAllPosts();
    PostResponseDTO updatePost(UpdatePostRequestDTO request);
    void deletePost(Long postId);
}
