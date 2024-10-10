package org.callboard.services.postsServices;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.PostListResponse;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.entities.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllPostsService {

    private final PostRepositoryService postRepoService;
    private final PostResponseListMapper postResponseListMapper;

    public PostListResponse findAllPosts() {

        List<Post> posts = postRepoService.findAll();

        return PostListResponse.builder()
                .responses(postResponseListMapper.mapPostsListToPostResponseList(posts))
                .build();
    }
}
