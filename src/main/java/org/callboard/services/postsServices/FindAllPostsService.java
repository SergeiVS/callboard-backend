package org.callboard.services.postsServices;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.PostListResponse;
import org.callboard.entities.Post;
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
