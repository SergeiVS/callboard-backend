package org.callboard.controllers.postsControllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.callboard.dto.StandardStringRequest;
import org.callboard.dto.postDto.NewPostRequest;
import org.callboard.dto.postDto.PostListResponse;
import org.callboard.dto.postDto.PostResponse;
import org.callboard.dto.postDto.UpdatePostRequest;
import org.callboard.services.postsServices.CreatePostService;
import org.callboard.services.postsServices.FindAllPostsService;
import org.callboard.services.postsServices.FindPostsBySubjectService;
import org.callboard.services.postsServices.UpdatePostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsControllers {

    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final FindAllPostsService findAllPostsService;
    private final FindPostsBySubjectService findPostsBySubjectService;


    @PostMapping
    public ResponseEntity<PostResponse> createNewPost(@Valid @RequestBody NewPostRequest request) {
        return createPostService.execute(request);
    }

    @PostMapping("/update")
    public ResponseEntity<PostResponse> updatePost(@Valid @RequestBody UpdatePostRequest request) {
        return updatePostService.execute(request);
    }

    @GetMapping
    public ResponseEntity<PostListResponse> getAllPosts() {
        return findAllPostsService.findAllPosts();
    }

    @GetMapping("/{subject}")
    public ResponseEntity<PostListResponse> getPostsBySubject(@PathVariable String subject) {
        return findPostsBySubjectService.execute(new StandardStringRequest(subject));
    }

}
