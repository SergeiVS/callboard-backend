package org.callboard.services.postsServices;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.postDto.PostListResponse;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.entities.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FindPostsBySubjectService implements PostServiceInterface<PostListResponse, StandardStringRequest>{

   private final PostRepositoryService postRepositoryService;
  private final PostResponseListMapper postResponseListMapper;

    @Override
    public PostListResponse execute(StandardStringRequest subjectName) {

        List<Post> posts = postRepositoryService.findBySubject(subjectName.getParameter().toUpperCase());

        return PostListResponse.builder()
                .responses(postResponseListMapper.mapPostsListToPostResponseList(posts))
                .build();
    }

}
