package org.callboard.services.PostService;

import org.callboard.dto.PostDto.NewPostRequestDTO;
import org.callboard.dto.PostDto.UpdatePostRequestDTO;
import org.callboard.dto.PostDto.entities.Post;
import java.util.List;

public interface PostService {
    Post createPost(NewPostRequestDTO request);
    Post updatePost(Long postId, UpdatePostRequestDTO request);
    void deletePost(Long postId);
    List<Post> getAllPosts();
    Post getPostById(Long postId);
}
