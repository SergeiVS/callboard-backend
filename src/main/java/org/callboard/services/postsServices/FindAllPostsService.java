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
public class FindAllPostsService {

    private final PostRepositoryService postRepoService;
    private final PostResponseListMapper postResponseListMapper;

    public ResponseEntity<List<PostResponse>> findAllPosts() {
        List<Post> posts = postRepoService.findAll();
        return new ResponseEntity<>(postResponseListMapper.mapPostsListToPostResponseList(posts), HttpStatus.FOUND);
    }
}
