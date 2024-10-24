package org.callboard.services.postsServices;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.postDto.PostListResponse;
import org.callboard.entities.Post;
import org.callboard.services.StandardServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindPostsBySubjectService implements StandardServiceInterface<PostListResponse, StandardStringRequest> {

    private final PostRepositoryService postRepositoryService;
    private final PostResponseListMapper postResponseListMapper;

    @Override
    public PostListResponse execute(StandardStringRequest subjectName) throws Exception {

        List<Post> posts = postRepositoryService.findBySubject(subjectName.getParameter().toUpperCase());

        return PostListResponse.builder()
                .responses(postResponseListMapper.mapPostsListToPostResponseList(posts))
                .build();
    }

}
