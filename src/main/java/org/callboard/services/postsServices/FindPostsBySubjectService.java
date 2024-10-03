package org.callboard.services.postsServices;

import lombok.RequiredArgsConstructor;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.entities.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FindPostsBySubjectService implements PostServiceInterface<String>{

   private final PostRepositoryService postRepositoryService;
  private final PostResponseListMapper postResponseListMapper;

    @Override
    public ResponseEntity<List<PostResponse>> execute(String subjectName) {

        List<Post> posts = postRepositoryService.findBySubject(subjectName.toUpperCase());

        return new ResponseEntity<>(postResponseListMapper.mapPostsListToPostResponseList(posts), HttpStatus.FOUND);
    }

}
