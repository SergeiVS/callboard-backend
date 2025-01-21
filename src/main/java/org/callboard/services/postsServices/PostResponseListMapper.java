package org.callboard.services.postsServices;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.entities.Post;
import org.callboard.mappers.PostMappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostResponseListMapper {

    private final PostMappers postMappers;

    public List<PostResponse> mapPostsListToPostResponseList(List<Post> posts) {
        return posts.stream()
                .map(postMappers::toPostResponse)
                .toList();
    }
}
